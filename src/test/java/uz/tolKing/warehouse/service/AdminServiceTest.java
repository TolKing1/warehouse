package uz.tolKing.warehouse.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceTest {
    private static AdminService adminService;
    private static Connection connection;
    private static Savepoint savepoint;

    @BeforeAll
    static void setUp() {
        String user = null,password = null;
        try (InputStream input = AdminServiceTest.class.getClassLoader().getResourceAsStream("admin.properties")) {
            assertNotNull(input, "Could not find admin.properties file");

            // Load the properties
            Properties properties = new Properties();
            properties.load(input);

            // Access properties as needed
            user = properties.getProperty("login");
            password = properties.getProperty("password");

            assertNotNull(user);
            assertNotNull(password);

        } catch (IOException e) {
            fail();
        }
        ConnectionService connectionService = new ConnectionService(user,password);
        connection = ConnectionService.getConnection();
        adminService = new AdminService();

        // Set up a savepoint
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("Test");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterAll
    static void tearDown() {
        // Rollback
        try {
            if (savepoint != null) {
                connection.rollback(savepoint);
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Reset auto-commit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void testGetUserList() {
        String userList = adminService.getUserList();
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
    }

    @Test
    public void testAdd() {
        String testUser = "test_user";
        String testPassword = "test_password";

        //test user
        adminService.add(testUser, testPassword);

        String userList = adminService.getUserList();
        assertTrue(userList.contains(testUser));
    }

}

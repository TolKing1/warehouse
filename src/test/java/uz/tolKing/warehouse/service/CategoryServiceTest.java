package uz.tolKing.warehouse.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class CategoryServiceTest {
    private static CategoryService categoryService;
    private static Connection connection;

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
        categoryService = new CategoryService();
    }

    @AfterAll
    static void tearDown() {
        // Rollback
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testAdd() {
        //test parameters
        String tableName = "test_table";
        Map<String, String> params = new HashMap<>();
        params.put("column1", "VARCHAR(255)");
        params.put("column2", "INT");

        // Perform the test
        categoryService.add(tableName, params);

        assertTrue(isTableExists(tableName));
    }
    private boolean isTableExists(String tableName) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM " + tableName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}

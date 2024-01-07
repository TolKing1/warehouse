package uz.tolKing.warehouse.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private static ProductService productService;
    private static Connection connection;
    private static String id;

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
        productService = new ProductService();
        id = String.valueOf(new Random().nextInt(1000000 - 1 + 1) + 1);

    }

    @AfterAll
    static void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testAddAndDelete() {
        // Provide test data
        String table = "dish";
        String[] items = {"",id, "PlateSet", "123.2", "43","ceramic","true"};

        // Add test item
        productService.add(table, items);

        ResultSet resultSet = productService.search(table, new String[]{"search","id",id});
        assertNotNull(resultSet);

        // Delete the test item
        productService.delete(table, id);

        // Verify the state of the database
        ResultSet resultSetDel = productService.search(table, new String[]{"search","id",id});
        try {
            assertFalse(resultSetDel.next());
        } catch (SQLException e) {
            fail();
        }
    }
}

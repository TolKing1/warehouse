package uz.tolKing.warehouse.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionDAOTest {

    @Test
    public void testConnectionGuest() {
        //correct username and password for your database
        String username = "guest";
        String password = "guest";

        try {
            ConnectionDAO connectionDAO = new ConnectionDAO(username, password);
            Connection connection = connectionDAO.getConnection();

            assertNotNull(connection, "Connection should not be null");
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testConnectionWithIncorrectCredentials() {
        //incorrect username and password to simulate a false case
        String incorrectUsername = "Asjdaojdas";
        String incorrectPassword = "k132j3j21"+ new Random().nextInt();

        assertThrows(SQLException.class, () -> {
            new ConnectionDAO(incorrectUsername, incorrectPassword);
        });
    }
}

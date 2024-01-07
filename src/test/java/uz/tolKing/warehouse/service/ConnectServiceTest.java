package uz.tolKing.warehouse.service;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectServiceTest {
    @Test
    public void testConnectionGuest() {
        //correct username and password for your database
        String username = "guest";
        String password = "guest";

        ConnectionService connectionDAO = new ConnectionService(username, password);
        Connection connection = ConnectionService.getConnection();

        assertNotNull(connection, "Connection should not be null");
    }
    @Test
    public void testConnectionWithIncorrectCredentials() {
        //incorrect username and password to simulate a false case
        String incorrectUsername = "Asjdaojdas";
        String incorrectPassword = "k132j3j21"+ new Random().nextInt();
        ConnectionService connectionDAO = new ConnectionService(incorrectUsername, incorrectPassword);
        Connection connection = ConnectionService.getConnection();

        assertNull(connection, "Connection should be null");
    }
}

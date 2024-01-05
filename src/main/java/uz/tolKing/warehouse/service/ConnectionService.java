package uz.tolKing.warehouse.service;

import java.sql.Connection;
import java.sql.SQLException;

import uz.tolKing.warehouse.dao.ConnectionDAO;

public class ConnectionService {

    private final ConnectionDAO connectionDAO;
    private Connection connection;


    public ConnectionService(String username,String password) {
        connectionDAO = new ConnectionDAO(username,password);
        try{
            connection = connectionDAO.getConnection();
        } catch (SQLException e) {
            connection = null;
            System.out.println("\n! Your username or password is incorrect. Try again !\n");
        }

    }

    public Connection getConnection() {
        return connection;
    }
    public String getUser(){
        try {
            return connection.getMetaData().getUserName();
        } catch (SQLException e) {
            return "\n! Can't find username !";
        }
    }

}

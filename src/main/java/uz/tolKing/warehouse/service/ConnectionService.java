package uz.tolKing.warehouse.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uz.tolKing.warehouse.dao.ConnectionDAO;

public class ConnectionService {

    protected static Connection connection;

    public ConnectionService(String username,String password) {
        try{
            ConnectionDAO connectionDAO = new ConnectionDAO(username, password);
            connection = connectionDAO.getConnection();
        } catch (SQLException e) {
            connection = null;
            System.out.println("\n! Your username or password is incorrect. Try again !\n");
        }

    }

    public static Connection getConnection() {
        return connection;
    }
    public String getUser(){
        try {
            return connection.getMetaData().getUserName();
        } catch (SQLException e) {
            return "\n! Can't find username !";
        }
    }
    public List<String> getTableNames(){
        List<String> tableList = new ArrayList<>();
        try {
            DatabaseMetaData md = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = md.getTables(null, null, "%", types);
            while (rs.next()) {
                tableList.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            System.out.println("\n! Can't get access to medata !");
        }
        return tableList;
    }

}

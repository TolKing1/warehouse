package uz.tolKing.warehouse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDAO extends DAOFactory {
    private final Connection connection;
    public ConnectionDAO(String username, String password) throws SQLException {
        Properties dbProperty = getConfig();
        this.url = "jdbc:postgresql://" + dbProperty.getProperty("host") + "/" + dbProperty.getProperty("dbName");
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
        connection = DriverManager.getConnection(this.url, this.properties);
    }

    public Connection getConnection(){
        return connection;
    }
}

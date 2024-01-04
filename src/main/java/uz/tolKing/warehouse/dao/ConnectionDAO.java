package uz.tolKing.warehouse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDAO extends DAOFactory {
    public ConnectionDAO(String username, String password) {
        Properties dbProperty = getConfig();
        this.url = "jdbc:postgresql://" + dbProperty.getProperty("host") + "/" + dbProperty.getProperty("dbName");
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}

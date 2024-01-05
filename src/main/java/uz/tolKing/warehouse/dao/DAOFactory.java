package uz.tolKing.warehouse.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAOFactory {
    protected String url;
    protected Properties properties;

    public static Properties getConfig() {
        Properties dbProperty = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            dbProperty.load(fileInputStream);
            return dbProperty;
        } catch (IOException e) {
            System.out.println("Properties file load error. Check it again");
            System.exit(1);
        }
        return null;
    }

    public abstract Connection getConnection() throws SQLException;
}

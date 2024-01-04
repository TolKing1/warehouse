package uz.tolKing.warehouse;

import uz.tolKing.warehouse.dao.ConnectionDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class WareHouseApp {
    public static void main(String[] args) throws SQLException {
        ConnectionDAO db = new ConnectionDAO("postgres","123");
        Connection con = db.getConnection();
    }
}

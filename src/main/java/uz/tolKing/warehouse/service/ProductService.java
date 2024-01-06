package uz.tolKing.warehouse.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static uz.tolKing.warehouse.dao.ConnectionDAO.connection;

public class ProductService {
    public static void delete(String table, int id){
        String SQL = "DELETE FROM %s WHERE id = ?".formatted(table);
        Connection connection = ConnectionService.getConnection();
        System.out.println("|".repeat(43));
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,id);
            int affected = preparedStatement.executeUpdate();
            //print
            System.out.printf("| %d item is successfully deleted |%n", affected);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("|".repeat(43)+"\n\n");
    }

    public static void add(String table,String[] items) {
        StringBuilder itemsString = new StringBuilder();
        for (int i = 1; i < items.length; i++) {
            itemsString.append('\'').append(items[i]).append('\'');
            if (i != items.length-1){
                itemsString.append(',');
            }
        }
        String SQL= "INSERT INTO %s VALUES (%s)".formatted(table,itemsString);

        System.out.println("|".repeat(43));
        try {
            Statement statement = connection.createStatement();
            int affected = statement.executeUpdate(SQL);
            //print
            System.out.printf("| %d item  is successfully added |%n", affected);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("|".repeat(43));


    }
}

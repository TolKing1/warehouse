package uz.tolKing.warehouse.service;

import java.sql.*;

import static uz.tolKing.warehouse.service.ConnectionService.connection;

public class ProductService {
    public void delete(String table, String id) {
        int idNum;
        boolean initialAutoCommit = false;

        //get autocommit state
        try {
            initialAutoCommit = connection.getAutoCommit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Savepoint savepoint = null;
        try {
            idNum = Integer.parseInt(id);
            //Manual commit
            try {
                connection.setAutoCommit(false);
                savepoint = connection.setSavepoint();

                String SQL = "DELETE FROM %s WHERE id = ?".formatted(table);
                System.out.println("|".repeat(30));

                //Execute
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                    preparedStatement.setInt(1, idNum);
                    int affected = preparedStatement.executeUpdate();
                    //print
                    System.out.printf("%d item has been successfully deleted%n", affected);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println("|".repeat(30) + "\n\n");

                connection.commit();
            } catch (SQLException e) {
                rollbackConnection(connection, savepoint);
            } finally {
                connection.setAutoCommit(initialAutoCommit);
            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("\n! Try with integer !\n");
        }
    }

    public void add(String table, String[] items) {
        StringBuilder itemsString = new StringBuilder();
        for (int i = 1; i < items.length; i++) {
            itemsString.append('\'').append(items[i]).append('\'');
            if (i != items.length - 1) {
                itemsString.append(',');
            }
        }
        boolean initialAutoCommit = false;

        //get autocommit state
        try {
            initialAutoCommit = connection.getAutoCommit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Savepoint savepoint = null;
        try {
            //Manual commit
            try {
                connection.setAutoCommit(false);
                savepoint = connection.setSavepoint();

                String SQL = "INSERT INTO %s VALUES (%s)".formatted(table, itemsString);

                System.out.println("|".repeat(30));
                try {
                    Statement statement = connection.createStatement();
                    statement.execute(SQL);
                    System.out.printf("Item has been successfully added%n");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("|".repeat(30));
                connection.commit();
            } catch (SQLException e) {
                rollbackConnection(connection, savepoint);
            } finally {
                connection.setAutoCommit(initialAutoCommit);
            }
        } catch (SQLException e) {
            System.out.println("\n! Try again !\n");
        }
    }
    public ResultSet search(String table,String[] items){
        String SQL = "SELECT * FROM %s WHERE %s = '%s'".formatted(table, items[1],items[2]);

        //Execute
        try {
            Statement statement = connection.createStatement();
             return statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public ResultSet sort(String table,String[] items){
        String SQL = "SELECT * FROM %s ORDER BY %s %s".formatted(
                table,
                items[1],
                ( items[2].toLowerCase().startsWith("d") )?"desc":"asc"
        );
        //Execute
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public ResultSet range(String table,String[] items){
        String SQL = "SELECT * FROM %s WHERE %s BETWEEN '%s' and '%s'".formatted(table, items[1],items[2],items[3]);
        //Execute
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    private static void rollbackConnection(Connection connection, Savepoint savepoint) throws SQLException {
        if (savepoint != null) {
            connection.rollback(savepoint);
        } else {
            connection.rollback();
        }
    }
}

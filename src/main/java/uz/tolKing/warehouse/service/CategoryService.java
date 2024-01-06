package uz.tolKing.warehouse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;


public class CategoryService {
    final Connection connection;

    public CategoryService() {
        this.connection = ConnectionService.getConnection();
    }
    public void add(String tableName, Map<String,String> params) {
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE ");
        createTableQuery.append(tableName).append(" (");
        createTableQuery.append("id").append(" ").append("SERIAL PRIMARY KEY").append(",");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            createTableQuery.append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
        }

        createTableQuery.deleteCharAt(createTableQuery.length() - 1); // Remove the last comma
        createTableQuery.append(")");
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

                System.out.println("|".repeat(25));
                try {
                    Statement statement = connection.createStatement();
                    statement.execute(createTableQuery.toString());
                    System.out.printf("Table has been successfully added%n");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("|".repeat(25));


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
    private static void rollbackConnection(Connection connection, Savepoint savepoint) throws SQLException {
        if (savepoint != null) {
            connection.rollback(savepoint);
        } else {
            connection.rollback();
        }
    }

}

package uz.tolKing.warehouse.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminService {
    final Connection connection;

    public AdminService() {
        this.connection = ConnectionService.getConnection();
    }

    static void printStatement(String query, Connection connection) {
        System.out.println("|".repeat(30));
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.printf("User  has been successfully added%n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("|".repeat(30));
    }

    public String getUserList() {
        StringBuilder userList = new StringBuilder();
        String query = "SELECT usename FROM pg_user";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int i = 1;
            while (resultSet.next()) {
                userList.append("   ").append(i).append(". ").append(resultSet.getString("usename")).append("\n");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userList.toString();
    }

    public void add(String user, String password) {
        String query = "CREATE USER %s WITH PASSWORD '%s';".formatted(user, password) +
                "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO %s;".formatted(user) +
                "SELECT pg_reload_conf();";
        printStatement(query, connection);
    }
}

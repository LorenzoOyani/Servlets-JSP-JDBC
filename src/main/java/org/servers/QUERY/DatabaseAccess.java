package org.servers.QUERY;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

public class DatabaseAccess {
    public static void main(String[] args) {
        DataSource source = CreateDataSource();
        try (Connection connection = source.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
//            insertIntoEmployee(connection);
            commandLineInsertion(connection);
            ResultSet rs = statement.executeQuery("SELECT * FROM employee_pojo");

            while (rs.next()) {
                System.out.println(STR."\{rs.getInt("id")} \{rs.getString("name")} \{rs.getString("department")}");
            }


        } catch (SQLException e) {
            e.getSQLState();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static DataSource CreateDataSource() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        String url = "jdbc:postgresql://localhost:5432/postgres";
        source.setURL(url);
        source.setUser("postgres");
        source.setPassword("Admin");
        return source;

    }

    public static void insertIntoEmployee(Connection connection) throws SQLException {
        String query = "INSERT INTO employee_pojo (id, name, department) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, 4);

            statement.setString(2, "mike");
            statement.setString(3, "business");
            int insertRow = statement.executeUpdate();
            System.out.printf("inserted into student:%s%n", insertRow);
        }
    }

    public static void commandLineInsertion(Connection connection) throws SQLException, IOException {
        String query = "INSERT INTO employee_pojo (id, name, department) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            do {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("enter id:");
                int id = Integer.parseInt(String.valueOf(reader.read()));
                System.out.println("enter name:");
                String name = String.valueOf(reader.read());
                System.out.println("enter department:");
                String department = String.valueOf(reader.read());

                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, department);

                int i = statement.executeUpdate();
                System.out.println(STR."\{i} record(s) affected");

                System.out.println("Do you want to continue? yes/no:");
                String response = reader.readLine();
                if (response.equalsIgnoreCase("no")) {
                    break;
                }

            } while (true);
        } catch (IOException | SQLException e) {
            System.err.println(STR."error occurred at: \{e.getMessage()}");
        } finally {
            if (connection != null) {
                connection.close();

            }
        }
    }
}

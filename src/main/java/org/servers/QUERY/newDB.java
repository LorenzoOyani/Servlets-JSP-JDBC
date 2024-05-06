package org.servers.QUERY;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;

public class newDB {

    public static void main(String[] args) {
        DataSource source = createDatasource();
        try (Connection connection = source.getConnection()) {
//            getAllInsults(connection);
//            getFilteredStudents(connection);
            insertIntoStudent(connection);
            updateStudentData(connection);
//            deleteStudent(connection);
        } catch (SQLException e) {
//            e.printStackTrace();
            String errorCode = e.getSQLState();
            if (Objects.equals(errorCode, "08000")) {
            } else if (Objects.equals(errorCode, "42601")) {

            }
        }
    }

    private static void getAllInsults(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student");
        ResultSet rs = stmt.executeQuery();
//        ResultSetMetaData metaData = rs.getMetaData();
//        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            System.out.printf("id:%d firstname:%s lastname:%s department:%s%n", rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("department"));

        }
    }

    private static DataSource createDatasource() {
        final String url =
                "jdbc:postgresql://localhost:5432/bird_encyclopedia?user=postgres&password=Admin";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        return dataSource;
    }

    // getting filtered entries in the DB.
    public static void getFilteredStudents(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM university_student WHERE firstname::text=?");
        statement.setString(1, "Mike");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.printf("id:%d insults:%s description:%s%n", rs.getInt("id"), rs.getString("firstInsult"), rs.getString("description"));

        }
    }

    public static void insertIntoStudent(Connection connection) throws SQLException {
//        connection.setAutoCommit(false);
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO university_student(id, firstname, lastname, department)  VALUES (?,?, ?, ?)");

        insertStatement.setInt(1, 1);
        insertStatement.setString(2, "John");
        insertStatement.setString(3, "Doe");
        insertStatement.setString(4, "Structural Engineering");
        int insertRow = insertStatement.executeUpdate();
        System.out.printf("inserted into student:%s%n", insertRow);
    }

    public static void updateStudentData(Connection connection) throws SQLException {
        String query = "UPDATE university_student SET  firstname = ?, lastname = ?, department = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "Douglas");
            statement.setString(2, "Baddie");
            statement.setString(4, "Philosophy");
            statement.setInt(1, 4);
            int updateRow = statement.executeUpdate();
            System.out.printf("updated student:%s%n", updateRow);
        }


    }

    public static void deleteStudent(Connection connection) throws SQLException {
        String query = "DELETE FROM university_student WHERE firstname = ?";
//        connection.setAutoCommit(false);
        PreparedStatement deleteStatement = connection.prepareStatement(query);
//        deleteStatement.setString(1, "Michael");
        deleteStatement.setString(1, "Jimmy");
        int deleteRow = deleteStatement.executeUpdate();
        System.out.printf("deleted rows: %s%n", deleteRow);

    }


}


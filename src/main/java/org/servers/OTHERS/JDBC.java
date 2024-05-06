package org.servers.OTHERS;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JDBC {

    public DataSource createDataSource() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/school_enrollment";
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser("postgres");
        dataSource.setPassword("Admin");
        dataSource.setSsl(false);
        return dataSource;
    }

    void main() throws SQLException {
        JDBC jdbc = new JDBC();
        DataSource dataSource = jdbc.createDataSource();
        try (Connection con = dataSource.getConnection()) {
            jdbc.getAllEnrollment(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getAllEnrollment(Connection conn) throws SQLException {
        String query = "SELECT * FROM school_enrollment";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                System.out.printf("id: %d, description: %s%n", rs.getInt("id"), rs.getString("enrollment"));
            }

        }
    }
}

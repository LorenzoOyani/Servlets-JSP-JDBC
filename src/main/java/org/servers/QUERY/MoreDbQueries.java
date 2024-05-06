package org.servers.QUERY;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MoreDbQueries {

    public static void main(String[] args) {
        DataSource connection = createDataSource();
        try (Connection con = connection.getConnection();
             Statement statement = con.createStatement()
        ) {
//            con.setAutoCommit(false);
//            String queryStatement = "SELECT * FROM top_clubs";
//            ResultSet rs = statement.executeQuery(queryStatement);
//            printResultSet(rs);
//
//            String sql = "INSERT INTO top_clubs (id, firstclub, secondclub, thirdclub, fourthclub) VALUES (?, ?, ?, ?, ?) WHERE id= ?";
//            PreparedStatement statement1 = con.prepareStatement(sql);
//
//            statement1.setInt(1, 2 );
//            statement1.setString(2, "juventus");
//            statement1.setString(3, "Ac milan");
//            statement1.setString(4, "Inter milan");
//            statement1.setString(5, "Roma");
//            int updateRow = statement1.executeUpdate();
//            System.out.printf("update row: id: %s%n", updateRow);

            String query = "DROP DATABASE bird_encyclopedia";
            statement.executeUpdate(query);
            System.out.println("Database dropped successfully!");



//        PrintResultSet(Connection);
        } catch (SQLException e) {
            e.getSQLState();
        }
    }
    public static DataSource createDataSource() {
        String url =
                "jdbc:postgresql://localhost:5432/bird_encyclopedia";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser("postgres");
        dataSource.setPassword("Admin");
        return dataSource;

    }

//    public static void insertIntoTopClubs(Connection con) {
//        String SQL = "INSERT INTO "
//    }

    public static void printResultSet(ResultSet set) throws SQLException {
        set.beforeFirst();
        while (set.next()) {
            System.out.println(STR."ID :\{set.getInt("id")}");
            System.out.println(STR."firstClub :\{set.getString("firstclub")}");
            System.out.println(STR."firstClub :\{set.getString("secondclub")}");
            System.out.println(STR."firstClub :\{set.getString("thirdclub")}");
            System.out.println(STR."firstClub :\{set.getString("fourthclub")}");

        }
        System.out.println();
    }
}

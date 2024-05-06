package org.servers.QUERY;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class InsertImageToDataBase {

    void main() {
        DataSource source = createDataSource();
        String sql = "CREATE TABLE IF NOT EXISTS imageTable (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(4000), " +
                "imageData BYTEA, " +
                "description VARCHAR(4000)" +
                ");";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
            System.out.println("sql table created successfully!");
            readImageFile(connection, "C:\\Users\\LAWRENCE\\Pictures\\Screenshots\\Screenshot 2024-04-25 072246.png", "servlet", "A servlet Image");
            retrieveImageFromDataBase(connection, "C:\\Users\\LAWRENCE\\Pictures\\Screenshots\\Screenshot 2024-04-25 072246.png" );

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public void retrieveImageFromDataBase(Connection connection, String path) throws SQLException {
        String query = "SELECT * FROM imageTable";
        try (PreparedStatement statement = connection.prepareStatement(query);
             FileOutputStream outputStream = new FileOutputStream(path);
             ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("imageData");
                if (blob != null) {
                    byte[] rawImageBytes = blob.getBytes(1, (int) blob.length());
                    outputStream.write(rawImageBytes);
                    System.out.println("Images retrieved and saved successfully");
                }else {
                    System.out.println("no data found on the image table");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void readImageFile(Connection connection, String path, String name, String description)
            throws IOException, SQLException {
        Path paths = Paths.get(path);

        byte[] imageData = Files.readAllBytes(paths);
        String QUERY = "INSERT INTO imageTable ( name, imageData, description) VALUES( ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setString(1, name);
            statement.setBytes(2, imageData);
            statement.setString(3, description);
            int rows = statement.executeUpdate();
            System.out.println(STR."\{rows} row(s) affected");
        }
    }



    public static DataSource createDataSource() {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser("postgres");
        dataSource.setPassword("Admin");
        return dataSource;

    }
}

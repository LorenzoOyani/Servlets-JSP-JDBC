package org.servers.QUERY;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;

public class StoreAndRetrieve {
    void main() {
        DataSource source = createDataSource();
        try (Connection connection = source.getConnection()) {

            retrieveImageFromDataBase(connection, "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveImageFromDataBase(Connection connection, String path) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM imageTable");
             FileWriter writer = new FileWriter(path);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Clob clob = rs.getClob("imageData");
                Reader reader = clob.getCharacterStream();

                int c;
                if ((c = reader.read()) != -1) {
                    writer.write((char) c);
                }

            }
            System.out.println("successfully retrieved!!!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void storeImage(Connection connection, String name, String path, String description) throws FileNotFoundException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO imageTable(name, filereader, description) VALUES (?, ?, ?)")
        ) {
            statement.setString(2, name);
            statement.setCharacterStream(3, fileReader);
            statement.setString(4, description);
            int i = statement.executeUpdate();
            System.out.println(STR."\{i}record affected");


        } catch (SQLException e) {
            e.printStackTrace();
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

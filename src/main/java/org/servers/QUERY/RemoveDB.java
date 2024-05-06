package org.servers.QUERY;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.servers.QUERY.InsertImageToDataBase.createDataSource;

public class RemoveDB {
    private static final DataSource source = createDataSource();

    void main() {
        int deleteRows = RemoveDB.deleteDB(4);
        System.out.println(STR."deleted rows \{deleteRows}");
    }

    public static int deleteDB(int id) {
        String sql = "DELETE FROM imageTable WHERE id = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

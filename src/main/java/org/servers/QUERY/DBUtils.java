package org.servers.QUERY;

import javax.sql.DataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import static org.servers.QUERY.InsertImageToDataBase.createDataSource;

public class DBUtils {
    private static final DataSource source = createDataSource();
    private static String preparedCase(String s) {
        try (Connection connection = source.getConnection();
             CallableStatement statement = connection.prepareCall("{ ? = call initcap( ? ) })")
        ){
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setString(2, s);
            statement.executeUpdate();
            return statement.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void main() {
        String greetings = DBUtils.preparedCase("hello anne");
        System.out.println(greetings);
    }
}

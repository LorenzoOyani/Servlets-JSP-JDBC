package org.servers.QUERY;

import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static org.servers.QUERY.InsertImageToDataBase.createDataSource;

public class TransactionManagement {

    private static final DataSource source =createDataSource();
    private static void getTransactions() {
        String sql = "INSERT INTO employee_pojo VALUES(?, ?, ?)";
        try(Connection connection = source.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
        // set auto-commit to false
            connection.setAutoCommit(false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter id: ");
                int s1 = Integer.parseInt(reader.readLine());
                System.out.println("Enter name: ");
                String s2 = reader.readLine();
                System.out.println("Enter department: ");
                String s3 = reader.readLine();

                statement.setInt(1, s1);
                statement.setString(2, s2);
                statement.setString(3, s3);
                System.out.println("rollBack/commit? ");
                String answer = reader.readLine();
                if (Objects.equals("commit", answer)) {
                    connection.commit();
                }
                if (Objects.equals("rollback", answer)) {
                    connection.rollback();

                }
                if (Objects.equals("no", answer)) {
                    break;
                }

            }
            System.out.println("committed successfully!");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    void main() {
        TransactionManagement.getTransactions();
    }
}

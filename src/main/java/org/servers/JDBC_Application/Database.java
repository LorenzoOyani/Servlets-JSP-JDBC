package org.servers.JDBC_Application;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class Database {
    private static final DataSource source = createDataSource();


    private static DataSource createDataSource() {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser("postgres");
        dataSource.setPassword("Admin");
        return dataSource;

    }

    public static void putData(String username, String password, String date_of_birth) {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id SERIAL PRIMARY KEY, " +
                "username VARCHAR(4000), " +
                "password VARCHAR(4000), " +
                "date_of_birth Date, " +
                "date_registered TIMESTAMP NOT NULL" +
                ")";

        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
            String sqlValue = "INSERT INTO user (username, password, date_of_birth, date_registered) VALUES (?, ?, ?, ?)";
            PreparedStatement statement1;
            statement1 = connection.prepareStatement(sqlValue);
            statement1.setString(1, username);
            statement1.setString(2, password);
            statement1.setDate(3, Date.valueOf(date_of_birth));
            Timestamp timestamp = getTimeStamp();
            statement1.setTimestamp(4, timestamp);
            statement1.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Timestamp getTimeStamp() {
        Date date = new Date(1970);
        return new Timestamp(date.getTime());
    }

    public static void getData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String sql = "SELECT * FROM user ORDER BY date_registered";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            PrintWriter output = response.getWriter();
            ResultSet resultSet = statement.executeQuery();
            response.setContentType("text/html");
            output.println("<p>");
            while (resultSet.next()) {
                System.out.println(STR."\{resultSet.getString(1)} \{resultSet.getString(2)} \{resultSet.getString(3)} \{resultSet.getString(4)}");
            }
            output.println("</p>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        String sql = "SELECT * FROM user WHERE username= ? AND password= ? ";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet set = statement.executeQuery();
            response.setContentType("text/html");
            PrintWriter output = response.getWriter();
            output.println("<p>");
            if (!set.next()) {
                if (username == null) {
                    throw new SQLException("username not found!");

                } else if (password == null) {
                    throw new SQLException("invalid password!");
                } else {
                    // if username and password is found in the database;
                    String method = request.getHeader("method");
                    if (Objects.equals("session", method)) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("username", username);
                    } else if (Objects.equals("cookie", method)) {
                        Cookie userCookie = new Cookie("username", username);
                        userCookie.setMaxAge(24 * 3600); // 1 Day expiry
                        response.addCookie(userCookie);

                    }
                    response.sendRedirect("home_user.jsp");
                }
            }
            output.println("</p>");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void delete_row(String username) {
        String sql = "DELETE FROM user WHERE username = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            int status = statement.executeUpdate();
            if (status > 0) {
//                System.out.println("username removed");
                throw new SQLException("this username is not found!");
            } else {
                throw new SQLException("This user not found!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void update_row(String username, String oldPassword, String newPassword) {
        String path = "UPDATE user SET password= ?, WHERE username= ? , password =?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(path)
        ) {
            int status = statement.executeUpdate();
            if (status > 0) {
                if (!isValidator.isValidate(username)) {
                    throw new SQLException("please create a valid camelCase username");
                }
                if (!isValidPassword(username, oldPassword)) {
                    throw new IllegalArgumentException("Invalid old password");
                }
                if (!isValidPasswordFormat(newPassword)) {
                    throw new IllegalArgumentException("Invalid new password format");
                }
            }
            if (status == 0) {
                throw new SQLException("Failed to update password, username not found!");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidPasswordFormat(String password) {
        return password.length() >= 8;
    }

    private static boolean isValidPassword(String username, String oldPassword) {
        String sql = "SELECT * FROM user WHERE username= ? AND password= ?";
        try (Connection connection = source.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            statement.setString(2, oldPassword);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

package org.servers.JDBC_Application;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Servlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter output = response.getWriter();
        response.setContentType("text/html");
        output.println("<!DOCTYPE html>");
        output.println("<>");

        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            String date_of_birth = request.getParameter("date_of_birth");
            String cssTag = "<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
            output.println(STR."<head><title>Servlet Home Page</title>\{cssTag}");
            output.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'></head>");
            output.println("<body>");
            String conditional = request.getParameter("button");
            if (conditional.contentEquals("Register")) {
                try {
                    Database.putData(name, password, date_of_birth);
                    output.println("<p>registration successfully</p>");
                    Database.getData(request, response);

                } catch (SQLException e) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dataJsp.jsp");
                    dispatcher.include(request, response);
                    output.println(STR."<h3>Entered in wrong format, exception occured!<br>Error!\{e}</h3>");

                    throw new RuntimeException(e);
                }
            } else if (conditional.contentEquals("login with session")) {
                response.setHeader("method", ("session"));
                try {
                    Database.login(name, password, request, response);
                } catch (Exception e) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("");
                    dispatcher.include(request, response);
                    output.println(STR."<h3>Entered in wrong format, exception occured!<br>Error!\{e}</h3>");
                    throw new RuntimeException(e);


                }
            } else if (conditional.contentEquals("login with cookie")) {
                response.setHeader("method", "cookie");
                try {
                    Database.login(name, password, request, response);
                } catch (Exception e) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("");
                    dispatcher.include(request, response);
                    output.println(STR."<h3>Entered in wrong format, exception occured!<br>Error!\{e}</h3>");
                    throw new RuntimeException(e);
                }

            } else if (conditional.contentEquals("update password")) {
                String oldUserName = request.getParameter("username");
                String oldPassword = request.getParameter("old_password");
                String newPassword = request.getParameter("new_password");
                String confirmPassword = request.getHeader("confirm_password");
                try {
                    if (newPassword == null || newPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
                        System.out.println("invalid password");
                        throw new Exception("Invalid password");
                    }
                    if (!newPassword.equals(confirmPassword)) {
                        throw new IOException("password do not match!");
                    }
                    Database.update_row(oldUserName, oldPassword, newPassword);
                } catch (Exception e) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("");
                    dispatcher.include(request, response);
                    output.println(STR."<h3>Entered in wrong format, exception occured!<br>Error!\{e}</h3>");
                    throw new RuntimeException(e);
                }


            } else if (conditional.contentEquals("logOut")) {
                HttpSession session = request.getSession(false);
                String user = (String) session.getAttribute(name);
                if (user != null) {
                    session.removeAttribute(user);
                    session.invalidate();
                    System.out.println(STR."Logged out, Remove column from (session): \{user}");
                } else {
                    // check if user-session is still stored in cookies,then Redirect.
                    Cookie[] cookies = request.getCookies();

                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            user = cookie.getName();
                            if (user.equals("username")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                                user = cookie.getValue();
                                break;
                            }
                            System.out.println(STR."Logged out, Removed column from (cookies): \{user}");
                        }
                    }
                    response.sendRedirect("data.jsp");
                }


            } else if (conditional.contentEquals("Delete")) {
                try {
                    Database.delete_row(name);
                } catch (Exception e) {
                    RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
                    rd.include(request, response);
                    output.println(STR."<h3>Query completed!<br>\{e}</h3>");
                }
            }
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
            output.println(STR."<h3>Entered in wrong format, exception occured!<br>Error!\{e}</h3>");
        } finally {
            output.println("</body>");
            output.close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}



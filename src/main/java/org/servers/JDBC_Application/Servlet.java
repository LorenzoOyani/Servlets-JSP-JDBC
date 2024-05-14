package org.servers.JDBC_Application;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter output = response.getWriter();
        response.setContentType("text/html");
        output.println("<!DOCTYPE html>");
        output.println("<>");
    }

}

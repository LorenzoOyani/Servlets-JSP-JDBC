package org.servers.OTHERS;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// Extend HttpServlet class

public class HelloForm extends HttpServlet {

        // Method to handle GET method request.
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

                // Set response content type
                response.setContentType("text/html");

                PrintWriter out = response.getWriter();
                String title = "Using GET Method to Read Form Data";
                String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";

                out.println(STR."\{docType}<html>\n<head><title>\{title}</title></head>\n<body bgcolor=\"#f0f0f0\">\n<h1 align=\"center\">\{title}</h1>\n<ul>\n  <li><b>First Name</b>: \{request.getParameter("first_name")}\n  <li><b>Last Name</b>: \{request.getParameter("last_name")}\n</ul>\n</body>\n</html>"
                );
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doGet(request, response);
        }
}
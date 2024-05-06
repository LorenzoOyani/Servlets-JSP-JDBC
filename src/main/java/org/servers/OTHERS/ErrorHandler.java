package org.servers.OTHERS;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class ErrorHandler extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Throwable throwable = (Throwable) request.getAttribute("java.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("java.servlet.error.status_code");
        String servletName = (String) request.getAttribute("java.servlet.error.servletName");

        if (servletName == null) {
            servletName = "unknown";
        }
        String requestUri = request.getRequestURI();

        if (requestUri == null) {
            requestUri = "unknown";
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Error/ Exception handling!";

        out.println(STR."<!DOCTYPE html><html>\n<head><title>\{title}</title></head>\n<body bgColor = \"#000000\" >\n ");

        if (throwable == null && statusCode == null) {
            out.println("<h2> error information not found </h2>");
            out.println(STR."please return to the <a href = \"\{response.encodeURL("http://localhost8080/")}\">Home page </a>");
        } else if (statusCode != null) {
            out.println(STR."status code : \{statusCode}");

        } else {
            out.println("<h2>Error information.</h2>");
            out.println(STR."servlet name \{servletName}<br></br>");
            out.println(STR."Throwable \{throwable.getClass().getName()}<br></br>");
            out.println(STR."uriException \{requestUri}<br></br>");
            out.println(STR."Exception message \{throwable.getMessage()}<br></br>");
        }
        out.println("</body>");
        out.println("</html>");


    }
}

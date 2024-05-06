package org.servers.OTHERS;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteCookie extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cookie cookie = null;
        Cookie[] cookies = null;

        response.setContentType("text/html");
        String title = "Delete cookies with servlet";
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE hml>");
        out.println(STR."<head><title>\{title}</title></head>");
        out.println("<body bgColor = \"#f0f0f0\" >\n");
        if (cookies != null) {
            out.println(" cookies name and value");
            for (Cookie value : cookies) {
                cookie = value;
                if (cookie.getName().compareTo("firstname") == 0) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.println(STR."deleted cookie \{cookie.getName()}<br>");
                    out.println("no cookie found in headers");
                }
                out.println(STR."cookie name :\{cookie.getName()}, ");
                out.println(STR."cookie value: \{cookie.getValue()}<br/>");
            }
        } else {
            out.println("<h2>no cookies found</h2>");
        }


    }
}

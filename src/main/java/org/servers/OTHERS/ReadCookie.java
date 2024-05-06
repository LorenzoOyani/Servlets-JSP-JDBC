package org.servers.OTHERS;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*Reading cookies with servlet */
public class ReadCookie extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;

        cookies = request.getCookies();
        response.setContentType("text/html");
        String title = "";
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println(STR."<head><title>\{title}</title></head");
        out.println("<body bgColor = \"#f0f0f0f0\"  >\n");
        if (cookies != null) {
            out.println("<h1>The headers containing cookies keys and values</h1>");
            for (Cookie value : cookies) {
                cookie = value;
                out.println(STR."cookies name: \{cookie.getName()}, ");
                out.println(STR."cookies value: \{cookie.getValue()} <br> ");
            }
        } else {
            out.println("<h2> No cookies found on the GET requested URL </h2>");
        }
        out.println("</body>");
        out.println("</html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
         doGet(request, response);
    }

}

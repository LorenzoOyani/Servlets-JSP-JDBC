package org.servers.OTHERS;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SweetCookie extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie firstname = new Cookie("firstName",  request.getParameter("firstname"));
        Cookie lastname = new Cookie("lastname",  request.getParameter("lastname"));

        // set cookies expiring date to 24 hours.
        firstname.setMaxAge(60*60*24);
        lastname.setMaxAge(60*60*24);

        // add both cookies in the response header.
        response.addCookie(firstname);
        response.addCookie(lastname);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "get cookie sessions";
        out.println("<!DOCTYPE html>");
        out.println(STR."<head><title>\{title}</title></head>");
        out.println("<body>");
        out.println(STR."<h1>\{title}</h1>");
        out.println("<ul>");
        out.println(STR."<li <b>first Name</b>: >\{request.getParameter("firstname")} </li>");
        out.println(STR."<li <b>last Name</b>: >\{request.getParameter("lastname")} </li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");



    }

}

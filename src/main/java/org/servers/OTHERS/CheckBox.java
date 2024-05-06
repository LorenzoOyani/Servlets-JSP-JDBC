package org.servers.OTHERS;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkBox")
public class CheckBox extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String title ="Reading checkBox data";

        PrintWriter out = response.getWriter();
        out.println("<DOCTYPE html>");
        out.println(STR."<head><title> \{title}t</title></head>");
        out.println("<h1 align = \"center\"  >"+ title +  "</h1>");
        out.println("<ul>");
        out.println("<li><b>Math Flag:  </b>"+ request.getParameter("maths")+ "</li>");
        out.println("<br>");
        out.println("<li<b>physics Flag:</b>"+ request.getParameter("physics") +   "</li>");
        out.println("<li><b>Chemistry flag: </b>" +request.getParameter("chemistry")  +"</li>");
        out.println();
        out.println("</ul>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

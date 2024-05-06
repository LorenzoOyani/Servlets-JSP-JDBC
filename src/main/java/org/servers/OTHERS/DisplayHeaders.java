package org.servers.OTHERS;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/displayHead")
public class DisplayHeaders extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String title = "Display header requests";

        PrintWriter out = response.getWriter();
        out.println("!<DOCTYPE> html");
        out.println("<html");
        out.println(STR."<head><title>\{title}</title></head>");
        out.println("<body>");
        out.println(STR."<h1>\{title}</h1>");
        out.println("<tr> <th>Header Name</th> <th>Header value</th> </tr>");

        Enumeration<String>  headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println(STR."<tr><td>\{headerName}</td>\n");
            String paramValue = request.getHeader(headerName);
            out.println(STR."<td>\{paramValue}</td> </tr>");

        }
        out.println("</table>\n</body>\n</hml>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

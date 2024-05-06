package org.servers.OTHERS;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/RequestParam")
public class RequestParams extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Reading All Form Params";
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println(STR."<head><title>\{title}</title></head>");
        out.println("<body style='background-color: #f0f0f0;'>");
        out.println(STR."<h1>\{title}</h1>");
        out.println("<table width='100%' border='1' align='center'>");
        out.println("<tr><th>Param Name</th><th>Param Values</th></tr>");

        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            out.println("<tr>");
            out.println(STR."<td>\{paramName}</td>");
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues != null && paramValues.length > 0) {
                out.println("<td>");
                if (paramValues.length == 1) {
                    out.println(paramValues[0]);

                } else {
                    out.println("<ul>");
                    for (String value : paramValues) {
                        out.println(STR."<li>\{value}</li>");
                    }
                    out.println("</ul>");

                }
                out.println("</td>");
            } else {
                out.println("<td>No header parameters was found!</ttd>");
            }
            out.println("</tr>");
        }


//        Enumeration<String> paramNames = request.getParameterNames();
//        while (paramNames.hasMoreElements()) {
//            String paramName = paramNames.nextElement();
//            out.println("<tr>");
//            out.println(STR."<td>\{paramName}</td>");
//            String[] paramValues = request.getParameterValues(paramName);
//
//            if (paramValues != null && paramValues.length > 0) { // null checks for empty values in the array
//                out.println("<td>");
//                if (paramValues.length == 1) {
//                    out.println(paramValues[0]);
//                } else {
//                    out.println("<ul>");
//                    for (String value : paramValues) {
//                        out.println(STR."<li>\{value}</li>");
//                    }
//                    out.println("</ul>");
//                }
//                out.println("</td>");
//            } else {
//                out.println("<td>No values found!</td>");
//            }
//
//            out.println("</tr>");
//        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

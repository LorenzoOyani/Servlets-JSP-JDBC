package org.servers.OTHERS;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/*
 * Http is a stateless protocol, when a client opens a web page a separate connection is made on the server
 * and the server does not recognise the previous request made by the former client.
 * */
public class sessionTracking extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        // Get the session creation time

        Date creationTime = new Date(session.getCreationTime());

        // get the last created time of the session
        Date lastCreatedTime = new Date(session.getLastAccessedTime());

        String title = "welcome Back  to my website Bro!";

        // store all the session tracking and data
        int visitCount = 0;
        String visitCountKey = "visit count";

        String userIDKey = "userId";
        String userID = "ABED";

       if(session.isNew()){
          title = "welcome to my site new website";
          session.setAttribute(userID, userIDKey);
       }else{
           visitCount = (int)session.getAttribute(visitCountKey);
           visitCount = visitCount + 1;
           userID = (String) session.getAttribute(visitCountKey);
       }
       session.setAttribute(visitCountKey, visitCount);


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html >");
        out.println(STR."<head><title>\{title}</title></head>");
        out.println("<body bgColor = \"#f0f0f0\" >\n");
        out.println("<table>");
        out.println("<tr bgColor = \"#949494 \"  >");
        out.println("<tr>");
        out.println("<td>id</td>");
        out.println(STR."<td>\{session.getId()}</td>");
        out.println("</td>");
        out.println("<tr>");
        out.println("<td>created time</td>");
        out.println(STR."<td>\{creationTime}</td>");
        out.println("</td>");
        out.println("<tr>");
        out.println("<td>last accessTime</td>");
        out.println(STR."<td>\{lastCreatedTime}</td>");
        out.println("</td>");
        out.println("<tr>");
        out.println("<td>UserId</td>");
        out.println(STR."<td>\{userID}</td>");
        out.println("</td>");
        out.println("<tr>");
        out.println("<td>Visit count</td>");
        out.println(STR."<td>\{visitCount}</td>");
        out.println("</td>");


        out.println("</table>");
        out.println("</tr>");
        out.println("</tr>");
        out.println("</body>");
        out.println("</html>");


    }
}

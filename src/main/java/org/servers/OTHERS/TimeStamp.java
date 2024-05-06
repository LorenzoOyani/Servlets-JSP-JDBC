package org.servers.OTHERS;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeStamp extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setIntHeader("refresh", 5);

        response.sendError(407, "Needed authentication");
        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        if (calendar.get(Calendar.AM_PM) == Calendar.AM && calendar.get(Calendar.AM_PM) == Calendar.PM) {

            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        String CT = hour + ":" + minute +": "+ seconds + am_pm;
        PrintWriter out = response.getWriter();
        out.println("<p>"+ CT + "</p>");



    }
}

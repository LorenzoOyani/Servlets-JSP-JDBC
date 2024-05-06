package org.servers.OTHERS;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class sqlDate {
    void main() {
        Date dates = new Date();
        long time = dates.getTime();
        System.out.printf(STR."java date is :\{dates.toString()}\n");

        java.sql.Date sqlDate = new java.sql.Date(time);
        System.out.printf(STR."sql java date is :\{sqlDate.toString()}\n");

        java.sql.Time time1 = new Time(time);
        System.out.printf(STR."java time is :\{time1.toString()}\n");

        java.sql.Timestamp timestamp = new Timestamp(time);
        System.out.printf(STR."java sql timeStamp is :\{timestamp.toString()}");




    }
}

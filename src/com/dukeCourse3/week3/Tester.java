package com.dukeCourse3.week3;
import java.util.Date;
import com.dukeCourse3.week3.*;

public class Tester {

    //public void testLogEntry() {
    public static void main(String[] args) {
//        LogEntry le = new LogEntry("1.2.3.4", new Date(),
//                "example request", 200, 500);
//        System.out.println(le);
//        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
//        System.out.println(le2);
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("week3Data/short-test_log.txt");
    }


}

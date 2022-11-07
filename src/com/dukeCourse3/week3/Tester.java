package com.dukeCourse3.week3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tester {

    //public void testLogEntry() {

    public static void testPrintIps(LogAnalyzer la){
        la.printAll();
    }

    public static void testUniqueIps(LogAnalyzer la){
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs");
    }

    public static void testPrintAllHigherThanNum(int num, LogAnalyzer la){
        la.printAllHigherThanNum(num);
    }

    public static void testUniqueIPVisitsOnDay(String monthDayString,LogAnalyzer la){
       ArrayList<String> response = la.uniqueIPVisitsOnDay(monthDayString);
       System.out.println("The date value is " + monthDayString + ", the unique IP's are " + response);
       System.out.println("The size of this list is " + response.size());
    }

    public static void testCountUniqueIPsInRange(int low, int high,LogAnalyzer la){
        int response = la.countUniqueIPsInRange(low, high);
        System.out.println("the number of IPs in range is " + response);
    }

    public static void testCountVisitsPerIP(LogAnalyzer la){
        HashMap<String,Integer> response = la.countVisitsPerIP();
        System.out.println("total number of IPs " + response.size());
        System.out.println("number of visits per IP: ");
        for(String key : response.keySet()){
            System.out.println(" ip " + key + " total visits value " + response.get(key));
        }
    }

    public static void testMostNumberVisitsByIP(LogAnalyzer la){
        int maxValue = la.mostNumberVisitsByIP();
        System.out.println("The max number of visits by a single IP is " + maxValue);
    }

    public static void testIPsMostVisits(LogAnalyzer la){
        ArrayList<String> response = la.iPsMostVisits();
        System.out.println("the IPs with most visits are: ");
        response.forEach(value -> {
            System.out.println(value);
        });

    }

    public static void testIPsForDays(LogAnalyzer la){
        HashMap<String, ArrayList<String>> response = la.IPsForDays();
        System.out.println("this are IPs by date ");
        response.forEach((key, value) -> {
            System.out.println(key +" "+ value);
        });
    }

    public static void main(String[] args) {

        String rootFolder = "week3Data";
        ArrayList<String> fileNameList = new ArrayList<>(Arrays.asList(
                "short-test_log.txt",
                "weblog-short_log.txt",
                "weblog2-short_log.txt",
                "weblog3-short_log.txt"
        ));
        for (String fileName : fileNameList ) {
            System.out.println(" ");
            System.out.println("results from " + fileName);
            LogAnalyzer la = new LogAnalyzer(rootFolder + "/" + fileName);
            //testPrintIps(la);
            //testUniqueIps(la);
            //testPrintAllHigherThanNum(400, la);
            //testUniqueIPVisitsOnDay("Mar 24", la);
            //testCountUniqueIPsInRange(300,399,la);
            //testCountVisitsPerIP(la);
            //testMostNumberVisitsByIP(la);
            //testIPsMostVisits(la);
            testIPsForDays(la);
        }

    }




}

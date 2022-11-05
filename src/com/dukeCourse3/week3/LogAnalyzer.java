package com.dukeCourse3.week3;

import java.util.*;
import com.dukeCourse3.week3.WebLogParser;

import edu.duke.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    private WebLogParser webLogParser = new WebLogParser();
    private HashMap<String, Integer> visitsPerIP;
    public LogAnalyzer(String filePath) {
        // complete constructor
        records = new ArrayList<>();
        readFile(filePath);
        visitsPerIP = countVisitsPerIP();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);


        for(String  line : fr.lines()){
            LogEntry row = webLogParser.parseEntry(line);
            records.add(row);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        return visitsPerIP.size();
        }


    public void printAllHigherThanNum(Integer num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIpsPerDay = new ArrayList<>();
        for(LogEntry le : records){
            String monthDayValue = le.getAccessTime().toString().substring(4,10);
            String ipAddress = le.getIpAddress();

            if(!uniqueIpsPerDay.contains(ipAddress) && someday.equals(monthDayValue)){
                uniqueIpsPerDay.add(ipAddress);
            }
        }

        return uniqueIpsPerDay;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIpsInRange = new ArrayList<>();
        for(LogEntry le : records){
            int statusCode = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if(statusCode <= high && statusCode >= low && !uniqueIpsInRange.contains(ipAddress)){
                uniqueIpsInRange.add(ipAddress);
            }
        }
        return uniqueIpsInRange.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> ipCounter = new HashMap<>();
        for(LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if(ipCounter.containsKey(ipAddr)){
                ipCounter.replace(ipAddr, ipCounter.get(ipAddr) + 1);
            } else {
                ipCounter.put(ipAddr,1);
            }
        }
        return ipCounter;
    }

    public int mostNumberVisitsByIP(){
        return Collections.max(visitsPerIP.values());
    }

    // pending iPsMostVisits
}

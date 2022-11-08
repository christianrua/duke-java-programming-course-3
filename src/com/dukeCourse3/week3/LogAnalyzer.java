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

    public ArrayList<String> iPsMostVisits() {
        int maxValueOfVisits = mostNumberVisitsByIP();
        ArrayList<String> IPsWithMaxVisits = new ArrayList<>();
        visitsPerIP.forEach((key, value) -> {
            if(value == maxValueOfVisits){
                IPsWithMaxVisits.add(key);
            }
        });
        return IPsWithMaxVisits;
    }

    // pending iPsForDays
    public HashMap<String, ArrayList<String>> IPsForDays(){
        HashMap<String, ArrayList<String>> IPsForDays = new HashMap<>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            String dateValue= le.getAccessTime().toString().substring(4,10);
            if(!IPsForDays.containsKey(dateValue)){
                ArrayList<String> datesList = new ArrayList<>();
                datesList.add(ip);
                IPsForDays.put(dateValue, datesList);
            } else {
                ArrayList<String> datesList = IPsForDays.get(dateValue);
                datesList.add(ip);
                IPsForDays.replace(dateValue, datesList);
            }
        }
        return IPsForDays;
    }

    public String dayWithMostIPVisits(){
        HashMap<String, ArrayList<String>> IPsForDays = IPsForDays();
        int maxNumberOfIPs = 0;
        String dateWithMostUPs = "";
        for(String key : IPsForDays.keySet()){
            int listSize = IPsForDays.get(key).size();
            if(listSize > maxNumberOfIPs){
                dateWithMostUPs = key;
                maxNumberOfIPs = listSize;
            }
        }
        return dateWithMostUPs;
    }

    public ArrayList<String> IPsWithMostVisitsOnDay(){
        String dayWithMostIPs = dayWithMostIPVisits();
        HashMap<String, Integer> countIPs = new HashMap<>();
        ArrayList<String> ipList = new ArrayList<>();
        for(String ip: IPsForDays().get(dayWithMostIPs)){
            if(!countIPs.containsKey(ip)){
                countIPs.put(ip,1);
            } else {
                countIPs.replace(ip, countIPs.get(ip) + 1);
            }
        }

        int maxNumberOfRepetitions = Collections.max(countIPs.values());
        for (String ip: countIPs.keySet()){
            if(countIPs.get(ip) == maxNumberOfRepetitions){
                ipList.add(ip);
            }
        }
        return ipList;
    }

}

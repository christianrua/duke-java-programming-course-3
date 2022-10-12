package com.dukeCourse3.week3;

import java.util.Date;

public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

//    public LogEntry(String line) {
//        //https://stackoverflow.com/questions/30956820/log-parsing-with-regex
//        String logRegexPattern = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] (\\S+) (\\S+)\\s*(\\S+)?\\s* (\\d{3}) (\\S+)";
//
//    }

    public LogEntry(String ip, Date time, String req, int status, int bytes){
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturned() {
        return bytesReturned;
    }

    public String toString() {
        return ipAddress + " " + accessTime + " " + request + " " + statusCode + " " + bytesReturned;
    }
}

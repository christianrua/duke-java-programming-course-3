package com.dukeCourse3.wee3;

import java.util.Date;

public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String line) {

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


}

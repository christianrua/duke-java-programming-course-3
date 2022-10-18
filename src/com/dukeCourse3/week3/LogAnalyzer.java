package com.dukeCourse3.week3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.duke.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        Pattern p = Pattern.compile(logRegexPattern);

        for(String line : fr.lines()){
            Matcher matcher = p.matcher(line);
            System.out.println(matcher.group(1) +" "+ matcher.group(1));
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}

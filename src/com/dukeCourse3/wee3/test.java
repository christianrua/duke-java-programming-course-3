package com.dukeCourse3.wee3;
java.util.regex

public class test {

    public static void main(String[] args){
        String stringValue = "110.76.104.12 - - [30/sep/2015:07:47:11 -400] ``GET //favicon.ico HTTP/1.1´´ 200 3246";
        for(String logComponent : stringValue.split("\\s+")){
            System.out.println("this is the content of " + logComponent);
        }
    }
}

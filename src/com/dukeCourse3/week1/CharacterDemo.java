package com.dukeCourse3.week1;

public class CharacterDemo {

    public void digitTest() {
        String test = "ABCabc0123456789';#!";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            if(Character.isDigit(ch)){
                System.out.println(ch + " is a digit, k value is "+k);
            }
            if (Character.isAlphabetic(ch)) {
                System.out.println(ch + " is alphabetic, k value is "+k);
            }
        }
    }

    public void conversionTest(){
        String test = "ABCDEFabcdef123!#";
        for(int k=0; k < test.length(); k++) {
            char ch = test.charAt(k);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch+" "+uch+" "+lch);
        }
    }
}

package com.dukeCourse3.week1;

public class WordPlay {

    public boolean isVowel(char ch){
        char charValue = Character.toLowerCase(ch);
        String vowels = "aeiou";
        if(vowels.indexOf(charValue) != -1){
            return true;
        } else {
            return false;
        }
    }

    public String replaceVowels(String phrase, char ch){
        StringBuilder modifyPhrase = new StringBuilder(phrase);
        for(int i=0; i < phrase.length(); i++){
            if(isVowel(modifyPhrase.charAt(i))){
                modifyPhrase.setCharAt(i,ch);
            }
        }
        return modifyPhrase.toString();
    }

    public String emphasize(String phrase, char ch) {
        char lowerChar = Character.toLowerCase(ch);
        StringBuilder modifyPhrase = new StringBuilder(phrase);
        for(int i=0; i < phrase.length(); i++){
            char currentChar = Character.toLowerCase(phrase.charAt(i));
            if(currentChar == lowerChar){
                if(i % 2 == 0){
                    modifyPhrase.setCharAt(i,'*');
                } else {
                    modifyPhrase.setCharAt(i,'+');
                }
            }
        }
        return modifyPhrase.toString();
    }

    public void testEmphasize(){
        String response = emphasize("dna ctgaaactga", 'a');
        System.out.println("the result from dna ctgaaactga a is " + response);
        response = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println("the result from Mary Bella Abracadabra a is " + response);
    }

    public void testReplaceVowels(){
        String response = replaceVowels("Hello World",'*');
        System.out.println("replace vowels value " + response);
    }

    public void testIsVowel(){
        boolean response = isVowel('F');
        System.out.println("the value for F is " + response);
        response = isVowel('a');
        System.out.println("the value for a is "+response);

    }
}

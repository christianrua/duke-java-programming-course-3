package com.dukeCourse3.week1;

import edu.duke.FileResource;

import java.util.Locale;

public class CaesarCipher {

    String normalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    private char handleUpperLowerCase(char charValue, String encryptedAlphabetUpper){
        String loweCaseAlphabet = normalAlphabet.toLowerCase();
        String encryptedAlphabetLower = encryptedAlphabetUpper.toLowerCase();

        if(Character.isUpperCase(charValue)){
            int normalAlphabetIndex = normalAlphabet.indexOf(charValue);
            char cipherCharValue = encryptedAlphabetUpper.charAt(normalAlphabetIndex);
            return cipherCharValue;
        } else {
            int normalAlphabetIndex = loweCaseAlphabet.indexOf(charValue);
            char cipherCharValue = encryptedAlphabetLower.charAt(normalAlphabetIndex);
            return cipherCharValue;
        }

    }

    public String encrypt(String input, int key){

        String encr = normalAlphabet.substring(key);
        encr = encr + normalAlphabet.substring(0,key);

        StringBuilder cipherMessage = new StringBuilder();

        for(int i=0; i< input.length(); i++) {
            char charValue = input.charAt(i);
            if (Character.isAlphabetic(charValue)) {
                char cipherCharValue = handleUpperLowerCase(charValue, encr);
                cipherMessage.append(cipherCharValue);
            } else {
                cipherMessage.append(charValue);
            }
        }
        return cipherMessage.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder cipherMessage = new StringBuilder();

        for(int i=0; i < input.length(); i++) {
            char charValue = input.charAt(i);
            String charValueResponse = "";
            if(i % 2 == 0){
                charValueResponse = encrypt(Character.toString(charValue),key1);
            } else {
                charValueResponse = encrypt(Character.toString(charValue),key2);
            }
            cipherMessage.append(charValueResponse);
        }

        return cipherMessage.toString();
    }

    public void testCaesar(){
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    public void testEncryptTwoKeys(){
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String response = encryptTwoKeys(message,8,21);
        System.out.println("the value from encryptTwoKeys is " + response);
    }
}

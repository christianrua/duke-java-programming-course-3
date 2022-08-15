package com.dukeCourse3.week1;

import com.dukeCourse3.week1.CaesarCipherHelper;

import java.util.List;

public class CaesarCipherTwo {
    private CaesarCipherHelper cch = new CaesarCipherHelper();
    private String alphabet = cch.alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int charIndex;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2){
        shiftedAlphabet1 = alphabet.substring(key1) +
                alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) +
                alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder newString = new StringBuilder(input);
        String key1Alphabet = this.shiftedAlphabet1;
        String key2Alphabet = this.shiftedAlphabet2;

        // Loop through the crypted string and build a new string
        for(int i = 0; i < input.length(); i++){
            String cryptoAlphabet;

            // Even numbers use key1 and odd numbers use key2
            if(i%2 == 0){
                cryptoAlphabet = key1Alphabet;
            } else {
                cryptoAlphabet = key2Alphabet;
            }

            char currentChar = input.toLowerCase().charAt(i);
            int currentIndex = alphabet.toLowerCase().indexOf(currentChar);

            // Filter out empty spaces and special characters
            if(currentChar != ' ' && currentIndex != -1) {

                // Make sure to set character to upper or lower case values based on the crypted string
                if(Character.isUpperCase(input.charAt(i))){
                    newString.setCharAt(i, cryptoAlphabet.charAt(currentIndex));
                } else {
                    newString.setCharAt(i, Character.toLowerCase(cryptoAlphabet.charAt(currentIndex)));
                }
            }
        }
        return newString.toString();
    }

    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cct.encrypt(input);
    }

}

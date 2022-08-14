package com.dukeCourse3.week1;

import java.util.Arrays;
import java.util.List;

public class CaesarCipherBest {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
    private int mainKey;
    private String shiftedAlphabetLowerCase;
    private String alphabetLowercase = alphabet.toLowerCase();
    private int charIndex;
    public CaesarCipherBest(int key){
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        shiftedAlphabetLowerCase = shiftedAlphabet.toLowerCase();
        mainKey = key;
    }

    private List<String> getTypeOfAlphabet(char c){

        List<String> alphabetComponents;
        if(Character.isWhitespace(c) == true){
            alphabetComponents = Arrays.asList("", "");
        } else {
            if (Character.isUpperCase(c) == true){
                alphabetComponents = Arrays.asList(alphabet, shiftedAlphabet);
            } else {
                alphabetComponents = Arrays.asList(alphabetLowercase, shiftedAlphabetLowerCase);
            }
        }

        return alphabetComponents;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        System.out.println("value of input " + input);
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            List<String> alphabetComponents = this.getTypeOfAlphabet(c);
            String alphabet = alphabetComponents.get(0);
            String shiftedAlphabet = alphabetComponents.get(1);

            if(alphabet!="" && shiftedAlphabet!=""){
                charIndex = alphabet.indexOf(c);
                c = shiftedAlphabet.charAt(charIndex);
                if(charIndex != -1){
                    c = shiftedAlphabet.charAt(charIndex);
                    sb.setCharAt(i, c);
                }
            }

        }
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipherBest cc = new CaesarCipherBest(26 - mainKey);
        return cc.encrypt(input);
    }
}

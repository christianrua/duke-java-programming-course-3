package com.dukeCourse3.week1;

public class CaesarCipherBest {
    private String alphabet;
    private String shiftedAlphabet;
    public CaesarCipherBest(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < sb.length(); i++){
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);
            if(idx != -1){
                c = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i, c);
            }
        }
        return sb.toString();
    }
}

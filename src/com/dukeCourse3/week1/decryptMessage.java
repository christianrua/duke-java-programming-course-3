package com.dukeCourse3.week1;

import com.dukeCourse3.week1.CaesarCipher;

public class decryptMessage {

    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1) {
                counts[dex] = counts[dex] + 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] array){
        int maxValue = 0;
        for (int k=0; k < array.length; k++){
            if (array[k] > array[maxValue]){
                maxValue = k;
            }
        }
        return maxValue;
    }

    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        System.out.println("dkey value is " + dkey + ", maxDex value is " + maxDex);
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public String halfOfString(String message, int start){

        StringBuilder halfStringOdd = new StringBuilder();
        StringBuilder halfStringEven = new StringBuilder();

        for(int i=0; i < message.length(); i++){
            char ch = message.charAt(i);
            if(Character.isAlphabetic(ch)) {
                if (i % 2 == 0) {
                    halfStringEven.append(message.charAt(i));
                } else {
                    halfStringOdd.append(message.charAt(i));
                }
            }
        }

        if(start == 0) {
            return halfStringEven.toString();
        } else {
            return halfStringOdd.toString();
        }

    }

    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4; // 4 is the index of E, the most common letter
        if(maxIndex < 4){
            dkey = 26 - (4-maxIndex);
        }

        return dkey;

    }

    public void decryptTwoKeys(String encrypted){
        String firstStringCharacter = halfOfString(encrypted, 0);
        String secondStringCharacter = halfOfString(encrypted, 1);

        int firstKey = getKey(firstStringCharacter);
        int secondKey = getKey(secondStringCharacter);

        System.out.println("The keys found are key1: " + firstKey + " key2: " + secondKey);
        CaesarCipher cc = new CaesarCipher();
        String decryptedMessage = cc.encryptTwoKeys(encrypted,26 - firstKey,26 - secondKey);
        System.out.println("The decrypted message is "+ decryptedMessage);
    }

    public void testDecryptTwoKeys(){
        decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx");
    }

    public void testHalfOfString(){
        String response1 = halfOfString("Qbkm Zgis", 0);
        System.out.println("response1 value "+response1);
        String response2 = halfOfString("Qbkm Zgis", 1);
        System.out.println("response2 value "+response2);
    }

    public void testDecrypt(){
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        CaesarCipher cc = new CaesarCipher();
        String encryptedMessage = cc.encrypt(message,15);
        System.out.println("the encrypted message is " + encryptedMessage);
        String decryptedMessage_2 = cc.encrypt(encryptedMessage,11);
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("the decrypted message is " + decryptedMessage + "    " + decryptedMessage_2);
    }
}

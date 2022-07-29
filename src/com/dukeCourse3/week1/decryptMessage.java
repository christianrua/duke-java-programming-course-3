package com.dukeCourse3.week1;

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
        StringBuilder halfString = new StringBuilder();


        return halfString.toString();
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

package com.dukeCourse3.week1;

public class cipher {

    public String returnCipherMessage(String originalMessage, int key) {
        String normalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encr = normalAlphabet.substring(key);
        encr = encr + normalAlphabet.substring(0,key);

        StringBuilder cipherMessage = new StringBuilder();

        for(int i=0; i<originalMessage.length(); i++) {
            char charValue = originalMessage.charAt(i);
            if (Character.isAlphabetic(charValue)) {
                int normalAlphabetIndex = normalAlphabet.indexOf(charValue);
                cipherMessage.append(encr.charAt(normalAlphabetIndex));
            } else {
                cipherMessage.append(charValue);
            }
        }
        return cipherMessage.toString();
    }
}

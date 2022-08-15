package com.dukeCourse3.week1;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    private CaesarCipherHelper cch = new CaesarCipherHelper();
    String alphabet = cch.alphabet;

    private String halfOfString(String message, int start){

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

    public String breakCaesarCipher(String input){
        String evenLetters = this.halfOfString(input, 0);
        String oddLetters = this.halfOfString(input,1);
        int dkey1 = cch.getEncryptionKey(evenLetters);
        int dkey2 = cch.getEncryptionKey(oddLetters);
        CaesarCipherTwo cct = new CaesarCipherTwo(dkey1,dkey2);
        return cct.decrypt(input);
    }

    public void simpleTests(){
        FileResource fr = new FileResource();
        String contents = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        String encryptedMessage = cct.encrypt(contents);
        System.out.println("the encrypted message with the two key class is " + encryptedMessage);
        String unencryptedMessage = cct.decrypt(encryptedMessage);
        System.out.println("the unencrypted message with the two key class is " + unencryptedMessage);
        System.out.println("-------------------- testing breaking Cipher");
        String breakedMessage = this.breakCaesarCipher(encryptedMessage);
        System.out.println("breakedMessage value " + breakedMessage);

    }



}

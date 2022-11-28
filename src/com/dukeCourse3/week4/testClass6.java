package com.dukeCourse3.week1;

import com.dukeCourse3.week4.*;
import com.dukeCourse3.week4.CaesarCipher;
import edu.duke.FileResource;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

public class testClass6 {

    public static void caesarCipherTester(){
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource("week4Data/titus-small.txt");
        String encryptedMessage = cc.encrypt(fr.asString());
        System.out.println("encryptedMessage value " + encryptedMessage);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        System.out.println("decryptedMessage value " + decryptedMessage);
    }

    public static void tryKeyLengthTest(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("week4Data/secretmessage2.txt");
        int[]  response = vb.tryKeyLength(fr.asString(),38,'e');
        System.out.println("response value " + Arrays.toString(response));
    }

    public static void mostCommonCharInTest(){
        FileResource fr = new FileResource("week4Data/dictionaries/English");
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> wordsDict = vb.readDictionary(fr);
        Character c = vb.mostCommonCharIn(wordsDict);
        System.out.println("the most common word in the english dict is " + c);
    }

    public static void main(String[] args) {
        //caesarCipherTester();
        //tryKeyLengthTest();
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
        //mostCommonCharInTest();
    }
}

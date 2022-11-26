package com.dukeCourse3.week4;
import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
                sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public String getKeyRepresentation(int[] keyLength) {
        StringBuilder sb = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        for (int i = 0; i < keyLength.length; i++){
            sb.append(alphabet.charAt(keyLength[i]));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        String encryptedString;
        for(int kIndex = 0; kIndex < klength; kIndex+= 1){
            encryptedString = sliceString(encrypted,kIndex, klength);
            int caesarCrackerValue = cc.getKey(encryptedString);
            key[kIndex] = caesarCrackerValue;
        }
        return key;
    }

    public void decrypt () {
        FileResource fr = new FileResource("");
        String vigenereMessage = fr.toString();
        VigenereBreaker vb = new VigenereBreaker();

    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> wordsDict = new HashSet<>();
        for(String word : fr.lines()){
            wordsDict.add(word.toLowerCase());
        }

        return wordsDict;
    }
    public int countWords(String message, HashSet<String> dictionary){
        int validWordsValue = 0;
        for(String word : message.split("\\W")){
            if(dictionary.contains(word.toLowerCase())){
                validWordsValue = validWordsValue + 1;
            }
        }

        return validWordsValue;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int bestRealWordsCount = 0;
        String bestDecryptedMessage = "";
        String keyValue = "";
        String bestKeyLengthString = "";

        for(int i=1; i <= 100; i++){
            int[] keyLength = tryKeyLength(encrypted,i,'e');
            int[] bestKeyLength = new int[keyLength.length];
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decryptedMessage = vc.decrypt(encrypted);
            int numberOfRealWords = countWords(decryptedMessage, dictionary);
            if(numberOfRealWords > bestRealWordsCount){
                bestRealWordsCount = numberOfRealWords;
                bestDecryptedMessage = decryptedMessage;
                bestKeyLength = keyLength;
                keyValue = getKeyRepresentation(bestKeyLength);
                bestKeyLengthString = Arrays.toString(bestKeyLength);
            }
        }

        System.out.println("bestRealWordsCount value " + bestRealWordsCount);
        System.out.println("bestKeyLength value " + bestKeyLengthString);
        System.out.println("keyValue value " + keyValue);
        return bestDecryptedMessage;

    }


    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        FileResource languageContent = new FileResource("week4Data/dictionaries/English");
        HashSet<String> languageDict = readDictionary(languageContent);
        String decryptedMessage = breakForLanguage(message, languageDict);
        System.out.println("the decrypted message is " + decryptedMessage);
    }

}

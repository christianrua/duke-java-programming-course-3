package com.dukeCourse3.week4;
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

}

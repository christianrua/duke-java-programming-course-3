package com.dukeCourse3.week1;

import java.util.Arrays;
import java.util.List;

public class CaesarCipherHelper {
    public String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public List<String> getTypeOfAlphabet(char c, String alphabet, String shiftedAlphabet){

        List<String> alphabetComponents;
        if(Character.isWhitespace(c) == true){
            alphabetComponents = Arrays.asList("", "");
        } else {
            if (Character.isUpperCase(c) == true){
                alphabetComponents = Arrays.asList(alphabet, shiftedAlphabet);
            } else {
                alphabetComponents = Arrays.asList(alphabet.toLowerCase(), shiftedAlphabet.toLowerCase());
            }
        }

        return alphabetComponents;
    }

    // Function to count how many letters there are in a text.
    // Returns an array with a count for each character in the alphabet -> A = 0;
    // E is the most used character in the english language.
    public int[] countLetters(String message){
        int [] occurrences = new int[26];

        for(int i = 0; i < message.length(); i++){
            int index = alphabet.toLowerCase().indexOf(message.toLowerCase().charAt(i));
            if (index != -1 ){
                occurrences[index]++;
            }
        }

        // Return the array
        return occurrences;
    }

    // Finds and returns the index of the highest number in an array
    // This should return the index of E
    public int maxIndex(int[] freqs){
        int index = 0;
        for(int i = 0; i < freqs.length; i++){
            if(freqs[i] > freqs[index]){
                index = i;
            }
        }
        return index;
    }

    public int getEncryptionKey(String encrypted){
        int [] freqs = this.countLetters(encrypted);
        int maxDex = this.maxIndex(freqs);
        int dkey = maxDex - 4; // 4 is the index of E, the most common letter
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        System.out.println("dkey = " + dkey);
        System.out.println("Key = " + (26-dkey));
        return dkey;
    }


}

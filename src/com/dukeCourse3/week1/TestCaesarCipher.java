package com.dukeCourse3.week1;

import edu.duke.FileResource;

public class TestCaesarCipher {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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

    public String decrypt1Key (String encrypted){
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // 4 is the index of E, the most common letter
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        System.out.println("dkey = " + dkey);
        System.out.println("Key = " + (26-dkey));
        //CaesarCipherBest cc = new CaesarCipherBest((26-dkey));
        CaesarCipherBest cc = new CaesarCipherBest(dkey);
        return cc.decrypt(encrypted);
    }

    public void simpleTests(){
        FileResource fr = new FileResource();
        String contents = fr.asString();
        CaesarCipherBest cc = new CaesarCipherBest(18);
        String encrypted_message = cc.encrypt(contents);
        System.out.println("the encrypted message is " + encrypted_message);
        String decrypted_message = cc.decrypt(encrypted_message);
        System.out.println("the decrypted message is " + decrypted_message + " \n");
        System.out.println("--------------------------------------------------------");
        String decrypted_message_from_breakCaesarCipher = breakCaesarCipher(encrypted_message);
        System.out.println("decrypted_message_from_breakCaesarCipher value " + decrypted_message_from_breakCaesarCipher);
    }

    public String breakCaesarCipher(String input){
        return decrypt1Key(input);
    }
}

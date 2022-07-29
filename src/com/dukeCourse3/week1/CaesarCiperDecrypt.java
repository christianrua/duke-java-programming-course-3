package com.dukeCourse3.week1;

public class CaesarCiperDecrypt {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Find the shifted alphabet based on a key value.
    // If the key is 3, the new alphabet starts at X
    private String getCryptoAlphabet(int key, String string){
        return string.substring(key) + string.substring(0,key);
    }

    // Encrypt a string based on a key value
    public String encryptString(String cryptedString, int key){
        StringBuilder newString = new StringBuilder(cryptedString);
        String cryptoAlphabet = getCryptoAlphabet(key, alphabet);

        // Loop through the crypted string, and build up a new string
        for(int i = 0; i < cryptedString.length(); i++){
            char currentChar = cryptedString.toLowerCase().charAt(i);
            int currentIndex = alphabet.toLowerCase().indexOf(currentChar);

            // Dont do anything with white spaces
            if(currentChar != ' ' && currentIndex != -1) {
                if(Character.isUpperCase(cryptedString.charAt(i))){
                    newString.setCharAt(i, cryptoAlphabet.charAt(currentIndex));
                } else {
                    newString.setCharAt(i, Character.toLowerCase(cryptoAlphabet.charAt(currentIndex)));
                }
            }
        }
        return newString.toString();
    }

    // Encrypt a string with two keys.
    public String encryptTwoKeys(String cryptedString, int key1, int key2){
        StringBuilder newString = new StringBuilder(cryptedString);
        String key1Alphabet = getCryptoAlphabet(key1, alphabet);
        String key2Alphabet = getCryptoAlphabet(key2, alphabet);

        // Loop through the crypted string and build a new string
        for(int i = 0; i < cryptedString.length(); i++){
            String cryptoAlphabet;

            // Even numbers use key1 and odd numbers use key2
            if(i%2 == 0){
                cryptoAlphabet = key1Alphabet;
            } else {
                cryptoAlphabet = key2Alphabet;
            }

            char currentChar = cryptedString.toLowerCase().charAt(i);
            int currentIndex = alphabet.toLowerCase().indexOf(currentChar);

            // Filter out empty spaces and special characters
            if(currentChar != ' ' && currentIndex != -1) {

                // Make sure to set character to upper og lower case vales based on the crypted string
                if(Character.isUpperCase(cryptedString.charAt(i))){
                    newString.setCharAt(i, cryptoAlphabet.charAt(currentIndex));
                } else {
                    newString.setCharAt(i, Character.toLowerCase(cryptoAlphabet.charAt(currentIndex)));
                }
            }
        }
        return newString.toString();
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

    // Decrypt function with 1 key
    public String decrypt1Key (String encrypted){
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // 4 is the index of E, the most common letter
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        System.out.println("Key = " + (26-dkey));
        return encryptString(encrypted, 26-dkey);
    }

    // Decrypt function with 2 keys
    public String decrypt2Key (String encrypted){
        // Divide the string to two, for each of the keys
        String oddString = "";
        String evenString = "";

        for(int i = 0; i < encrypted.length(); i++){
            if(i %2 == 0){
                oddString += encrypted.charAt(i);
            } else {
                evenString += encrypted.charAt(i);
            }
        }

        // Find the frequencies and add them together in one array
        int [] oddFreqs = countLetters(oddString);
        int [] evenFreqs = countLetters(evenString);

        // Print out the results in the array
        for(int i = 0; i < oddFreqs.length; i++){
            // System.out.println("Numbers of " + alphabet.substring(i, i+1).toUpperCase() + "'s : " + oddFreqs[i]);
        }

        // Print out the results in the array
        for(int i = 0; i < evenFreqs.length; i++){
            // System.out.println("Numbers of " + alphabet.substring(i, i+1).toUpperCase() + "'s : " + evenFreqs[i]);
        }

        // Find the key for the odd letters
        int maxDex1 = maxIndex(oddFreqs);
        int dkey1 = maxDex1 - 4; // 4 is the index of E, the most common letter
        if(maxDex1 < 4){
            dkey1 = 26 - (4-maxDex1);
        }
        System.out.println("Key1 = " + (26-dkey1));

        // Find the key for the even letters
        int maxDex2 = maxIndex(evenFreqs);
        int dkey2 = maxDex2 - 4; // 4 is the index of E, the most common letter
        if(maxDex2 < 4){
            dkey2 = 26 - (4-maxDex2);
        }
        System.out.println("Key2 = " + (26-dkey2));

        return encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
    }

    public void testDecrypt(){
        String message = "Hi, how are you, I am testing the encrypt and decrypt function, but it looks like is not working";
        String encryptedMessage = encryptString(message,15);
        System.out.println("the encrypted message is " + encryptedMessage);
        String decryptedMessage_2 = encryptString(encryptedMessage,11);
        String decryptedMessage = decrypt1Key(encryptedMessage);
        System.out.println("the decrypted message is " + decryptedMessage + "    " + decryptedMessage_2);
    }
}

package com.dukeCourse3.week4;
import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    private String lowerCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
    private Map<Character,Integer> charsMap = new HashMap<Character, Integer>();

    private void resetCharMap(){
        for(char c : lowerCaseAlphabet.toCharArray()){
            charsMap.put(c,0);
        }
    }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
                sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public String getKeyRepresentation(int[] keyLength) {
        StringBuilder sb = new StringBuilder();
        String alphabet = lowerCaseAlphabet;
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

    public List<Object> breakForLanguage(String encrypted, HashSet<String> dictionary){
        int bestRealWordsCount = 0;
        String bestDecryptedMessage = "";
        String keyValue = "";
        String bestKeyLengthString = "";

        for(int i=1; i <= 100; i++){
            char languageChar = mostCommonCharIn(dictionary);
            int[] keyLength = tryKeyLength(encrypted,i,languageChar);
            int[] bestKeyLength = new int[keyLength.length];
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decryptedMessage = vc.decrypt(encrypted);
            int numberOfRealWords = countWords(decryptedMessage, dictionary);
//            if(i == 38){
//                System.out.println("for the i == 38 the numbers of words is " + numberOfRealWords);
//            }
            if(numberOfRealWords > bestRealWordsCount){
                bestRealWordsCount = numberOfRealWords;
                bestDecryptedMessage = decryptedMessage;
                bestKeyLength = keyLength;
                keyValue = getKeyRepresentation(bestKeyLength);
                bestKeyLengthString = Arrays.toString(bestKeyLength);
            }
        }

//        System.out.println("bestRealWordsCount value " + bestRealWordsCount);
//        System.out.println("bestKeyLength value " + bestKeyLengthString);
//        System.out.println("keyValue value " + keyValue);
        return Arrays.asList(bestDecryptedMessage,bestRealWordsCount);
    }

    private HashMap<String, HashSet<String>> createLanguagesDict(){
        HashMap<String, HashSet<String>> languagesDict = new HashMap<>();

        ArrayList<String> languagesList = new ArrayList<>(Arrays.asList("Danish",
                "Dutch",
                "English",
                "French",
                "German",
                "Italian",
                "Portuguese",
                "Spanish"));
        for(String language : languagesList){
            System.out.println("loading the dict for the language " + language);
            FileResource fr = new FileResource("week4Data/dictionaries/"+language);
            languagesDict.put(language, readDictionary(fr));
            System.out.println("the dict has been loaded");
        }

        return languagesDict;
    }

    public Character mostCommonCharIn(HashSet<String> dictionary){
        char mostCommonChar;
        resetCharMap();
        for (String word : dictionary) {
            for(int i = 0; i < word.length(); i++){
                char c = Character.toLowerCase(word.charAt(i));
                if(charsMap.containsKey(c)){
                    int prevValue = charsMap.get(c);
                    charsMap.put(c, prevValue + 1);
                }

            }
        }
        mostCommonChar = charsMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst()
                .get().getKey();
        return mostCommonChar;
    }

    public void breakForAllLangs(String encrypted ){
        System.out.println("Checking the different languages");
        HashMap<String, HashSet<String>> languagesDict = createLanguagesDict();
        String bestDecryptedMessage = "";
        int bestWordCount = 0;
        String languageIdentified = "";

        for(String language : languagesDict.keySet()){
            System.out.println("checking the language " + language);
            List<Object> result = breakForLanguage(encrypted, languagesDict.get(language));
            String decryptedMessage = (String) result.get(0);
            int wordCount = (int) result.get(1);
            if(wordCount > bestWordCount){
                bestWordCount = wordCount;
                bestDecryptedMessage = decryptedMessage;
                languageIdentified = language;
            }
        }

        System.out.println("language identified " + languageIdentified);
        System.out.println("number of words " + bestWordCount);
        System.out.println("decrypted message content " + bestDecryptedMessage);

    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        breakForAllLangs(message);


    }

}

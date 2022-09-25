package com.dukeCourse3.week2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

//Create a new class called WordsInFiles. Put all the remaining listed items in this class.
public class WordsInFiles {
    //Create a private variable to store a HashMap that maps a word to an ArrayList of filenames.
    private HashMap<String, ArrayList<String>> wordsMap;

    //Write a constructor to initialize the HashMap variable.
    public WordsInFiles(){
        wordsMap = new HashMap<>();
    }

    //Write a private void method named addWordsFromFile that has one parameter f of type File. This method should add
    // all the words from f into the map. If a word is not in the map, then you must create a new ArrayList of type
    // String with this word, and have the word map to this ArrayList. If a word is already in the map, then add the
    // current filename to its ArrayList, unless the filename is already in the ArrayList. You can use the File method
    // getName to get the filename of a file.
    //

    private void addWordsFromFile(File f){
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        for (String word: fr.words()){
            boolean isKey = wordsMap.containsKey(word);
            if(isKey){
                ArrayList<String> fileNames = wordsMap.get(word);
                boolean isFile = fileNames.contains(fileName);
                if(!isFile){
                    fileNames.add(fileName);
                }
            } else {
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(fileName);
                wordsMap.put(word, fileNames);
            }
        }


    }

    //Write a void method named buildWordFileMap that has no parameters. This method first clears the map, and then
    // uses a DirectoryResource to select a group of files. For each file, it puts all of its words into the map by
    // calling the method addWordsFromFile. The remaining methods to write all assume that the HashMap has been built.
    //
    public void buildWordFileMap(){
        wordsMap = new HashMap<String, ArrayList<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    //Write the method maxNumber that has no parameters. This method returns the maximum number of files any word
    // appears in, considering all words from a group of files. In the example above, there are four files considered.
    // No word appears in all four files. Two words appear in three of the files, so maxNumber on those four files
    // would return 3. This method assumes that the HashMap has already been constructed.
    //
    private int maxNumber(){
        int maxNumberOfFiles = 0;
        for(String key: wordsMap.keySet()){
            int numberOfFiles = wordsMap.get(key).size();
            if(numberOfFiles > maxNumberOfFiles){
                maxNumberOfFiles = numberOfFiles;
            }
        }
        return maxNumberOfFiles;
    }
    //Write the method wordsInNumFiles that has one integer parameter called number. This method returns an ArrayList
    // of words that appear in exactly number files. In the example above, the call wordsInNumFiles(3) would return an
    // ArrayList with the words  “cats” and “and”, and the call wordsInNumFiles(2) would return an ArrayList with the
    // words “love”, “are”, and “dogs”, all the words that appear in exactly two files.
    //
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordsInFiles = new ArrayList<String>();
        for(String key: wordsMap.keySet()){
            if(number == wordsMap.get(key).size()){
                wordsInFiles.add(key);
            }
        }

        return wordsInFiles;
    }
    //Write the void method printFilesIn that has one String parameter named word. This method prints the names of the
    // files this word appears in, one filename per line. For example, in the example above, the call
    // printFilesIn(“cats”) would print the three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a
    // separate line.
    //
    public void printFilesIn(String word){
        ArrayList<String> fileNames = wordsMap.get(word);
        System.out.println(word +" "+ fileNames);
    }

    private void printMapBuild(){
        System.out.println("wordsMap build it.");
        for(String key : wordsMap.keySet()){
            System.out.println( key + " " + wordsMap.get(key).toString());
        }
    }
    //Write the void method tester that has no parameters. This method should call buildWordFileMap to select a group
    // of files and build a HashMap of words, with each word mapped to an ArrayList of the filenames this word appears
    // in, determine the maximum number of files any word is in, considering all words, and determine all the words
    // that are in the maximum number of files and for each such word, print the filenames of the files it is in.
    // (optional) If the map is not too big, then you might want to print out the complete map, all the keys, and for
    // each key its ArrayList. This might be helpful to make sure the map was built correctly.
    public void tester(){
        buildWordFileMap();
        printMapBuild();
        int maxWordTimesInFiles = maxNumber();
        System.out.println("the maximum number of files any word is in " + maxWordTimesInFiles);
        ArrayList<String> maxWordsList = wordsInNumFiles(maxWordTimesInFiles);
        System.out.println("those max words are: ");
        for(String word : maxWordsList){
            printFilesIn(word);
        }
    }
}

package com.dukeCourse3.week2;

import edu.duke.FileResource;
import edu.duke.StorageResource;
import edu.duke.URLResource;

import java.io.File;
import java.util.Locale;
import java.util.Random;

public class CountWords {

    StorageResource myWords;

    public CountWords() {
        myWords = new StorageResource();
    }

    public int getCount(){
        return myWords.size();
    }

    public void readWords(String source){
        myWords.clear();
        if (source.startsWith("http")){
            URLResource resource = new URLResource(source);
            for(String word: resource.words()){
                myWords.add(word.toLowerCase(Locale.ROOT));

            }
        } else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                myWords.add(word.toLowerCase());
            }
        }

    }

    public String getRandomWord(String[] words){
        Random rand = new Random();
        int index = rand.nextInt(words.length);
        return words[index];
    }
}

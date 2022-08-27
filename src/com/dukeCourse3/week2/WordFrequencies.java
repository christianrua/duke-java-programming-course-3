package com.dukeCourse3.week2;

import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public void tester(){
        this.findUnique();
        System.out.println("# unique words: "+ myWords.size());
        for(int k=0; k < myWords.size(); k++){
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
    }
}

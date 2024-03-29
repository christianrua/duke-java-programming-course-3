package com.dukeCourse3.week2;

import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Locale;

public class WordFrequenciesMap {
    public void countWords(){
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int total = 0;
        for(String w : fr.words()){
            w = w.toLowerCase();
            if(map.keySet().contains(w)){
              map.put(w, map.get(w) + 1);
            } else {
              map.put(w, 1);
            }
        }
        for(String w : map.keySet()){
            int ocurrences = map.get(w);
            if(ocurrences > 500){
                System.out.println(ocurrences+"\t"+w);
            }
        }
    }
}

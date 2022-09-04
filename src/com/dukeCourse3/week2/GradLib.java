package com.dukeCourse3.week2;

import java.util.ArrayList;
import java.util.Random;

public class GradLib {

    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private Random myRandom;

    private String dataSourceURL = "https://www.dukelearntoprogram.com/java/madtemplate2.txt";
    private String dataSourceDirectory = "week2Data/data";

    public GradLib() {
        initializedFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    private void initializedFromSource(String source){
        adjectiveList = readIt(source+"/adjective.txt");
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb,txt");
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label){
        if(label.equals("country")){
            return randomFrom(countryList);
        }
        if(label.equals("color")){
            return randomFrom(colorList);
        }
        if(label.equals("noun")){
            return randomFrom(nounList);
        }
        if(label.equals("name")){
            return randomFrom(nameList);
        }
        if(label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if(label.equals("animal")){
            return randomFrom(animalList);
        }
        if(label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if(label.equals("verb")){
            return randomFrom(verbList);
        }
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if(first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1, last));
       return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;

    }

    public void makeStory(){

    }
}

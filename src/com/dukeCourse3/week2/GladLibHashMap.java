package com.dukeCourse3.week2;
import edu.duke.*;
import java.util.*;
import java.util.HashMap;

public class GladLibHashMap {

    private HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
    private String[] topics = new String[] {
            "adjective",
            "noun",
            "color",
            "country",
            "name",
            "animal",
            "timeframe",
            "verb",
            "fruit"
    };
//    private ArrayList<String> adjectiveList;
//    private ArrayList<String> nounList;
//    private ArrayList<String> colorList;
//    private ArrayList<String> countryList;
//    private ArrayList<String> nameList;
//    private ArrayList<String> animalList;
//    private ArrayList<String> timeList;
//    private ArrayList<String> verbList;
//    private ArrayList<String> fruitList;
//    private ArrayList<String> seenWords;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/datalong";
    private static String dataSourceDirectory = "week2Data/data";

    public GladLibHashMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibHashMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {

        for(String topic : topics){
            ArrayList<String> aux = readIt(source+"/"+topic+".txt");
            myMap.put(topic, aux);
        }
        myMap.put("seenWords", new ArrayList<String>());
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if(myMap.containsKey(label)){
            if(label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
            } else {
                return randomFrom(myMap.get(label));
            }
        } else {
            return "**UNKNOWN**";
        }

    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));

        if(myMap.get("seenWords").size() == 0){
            myMap.get("seenWords").add(sub);
        }

        for (int i = 0; i < myMap.get("seenWords").size(); i++) {
            String word = myMap.get("seenWords").get(i);
            if(word.equals(sub)){
                sub = getSubstitute(w.substring(first+1,last));
                i = 0;
            }
        }

        myMap.get("seenWords").add(sub);

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    // I`m here trying to the totalWordsInMap function

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory+"/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total number of words that where replaced right " + Integer.toString(myMap.get("seenWords").size()));
        myMap.put("seenWords", new ArrayList<String>());
    }



}

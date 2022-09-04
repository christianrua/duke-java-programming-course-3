package com.dukeCourse3.week2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> charactersNames;
    private ArrayList<Integer> characterCounts;

    public CharactersInPlay() {
        charactersNames = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }

    public void update(String person){
        boolean updateFlag = false;
        for(int k=0; k < charactersNames.size(); k++){
            if(charactersNames.get(k).equals(person)){
                int actualValue = characterCounts.get(k);
                characterCounts.set(k, actualValue + 1);
                updateFlag = true;
                break;
            }
        }

        if(updateFlag == false){
            charactersNames.add(person);
            characterCounts.add(1);
        }
    }

    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            // process each line in turn
            int dotIndex = line.indexOf('.');
            if(dotIndex != -1){
                this.update(line.substring(0, dotIndex));
            }
        }
    }

    public void tester() {
        this.findAllCharacters();
        for(int k=0; k < charactersNames.size(); k++){
            String characterName = charactersNames.get(k);
            int occurrences = characterCounts.get(k);
            if(occurrences > 1){
                System.out.println("The character " + characterName + " has " +  Integer.toString(occurrences));
            }

        }
    }

    public void characterWithNumParts(int num1, int num2){
        this.findAllCharacters();
        for(int k=0; k < charactersNames.size(); k++){
            String characterName = charactersNames.get(k);
            int occurrences = characterCounts.get(k);
            if(num1 <= occurrences && occurrences <= num2){
                System.out.println("The character " + characterName + " has " +  Integer.toString(occurrences));
            }

        }
    }
}

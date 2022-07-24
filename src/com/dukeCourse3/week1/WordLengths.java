package com.dukeCourse3.week1;

import edu.duke.FileResource;

public class WordLengths {

    public int[] countWordLengths(FileResource resource, int[] counts){

        for (String word : resource.words()) {
            // process each line in turn
            int numberOfCharacters = 0;
            for(int k=0; k < word.length(); k++){
                if(Character.isLetter(word.charAt(k))){
                    numberOfCharacters += 1;
                } else if(word.charAt(k) == '-' || word.charAt(k) == '\''){
                    if( k + 1 <= word.length() && Character.isLetter(word.charAt(k + 1))){
                        numberOfCharacters += 1;
                    }
                }
            }

            if(numberOfCharacters >= 31) {
                counts[31] = counts[31] + 1;
            } else {
                counts[numberOfCharacters] = counts[numberOfCharacters] + 1;
            }
        }

        return counts;
    }

    public int indexOfMax(int[] values){
        int largestValueIndex = 0;
        int maxNumberOfItems = 0;

        for(int k=0; k < values.length; k++){
            if(values[k] > maxNumberOfItems) {
                maxNumberOfItems = values[k];
                largestValueIndex = k;
            }
        }

        return largestValueIndex;
    }

    public void testCountWordLengths(){
        FileResource f = new FileResource();
        int[] counts = new int[31];

        counts = countWordLengths(f, counts);

        for(int k=0; k < counts.length;k++) {
            System.out.println("length " + k + " has " + counts[k] + "\t" );
        }

        int mostCommonWordLength = indexOfMax(counts);
        System.out.println("The most common word length is " + mostCommonWordLength);

    }
}

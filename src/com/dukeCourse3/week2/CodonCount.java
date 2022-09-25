package com.dukeCourse3.week2;

import edu.duke.FileResource;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.stream.Collectors;

// Write a program to find out how many times each codon occurs in a strand of DNA based on reading frames.
// A strand of DNA is made up of the symbols C, G, T, and A. A codon is three consecutive symbols in a strand of DNA
// such as ATT or TCC. A reading frame is a way of dividing a strand of DNA into consecutive codons.
//
// Consider the following strand of DNA = “CGTTCAAGTTCAA”.
//
// There are three reading frames.
//
// The first reading frame starts at position 0 and has the codons: “CGT”, “TCA”, “AGT” and “TCA”.
// Here TCA occurs twice and the others each occur once.
//
// The second reading frame starts at position 1 (ignoring the first C character) and has the codons: “GTT”, “CAA”,
// “GTT”, “CAA”. Here both GTT and CAA occur twice.
//
// The third reading frame starts at position 2 (ignoring the first two characters CG) and has the codons: “TTC”,
// “AAG”, “TTC”. Here TTC occurs twice and AAG occurs once.
//
// A map of DNA codons to the number times each codon appears in a reading frame would be helpful in solving this
// problem.




public class CodonCount {

    //Create a private variable to store a HashMap to map DNA codons to their count
    private HashMap<String, Integer> dnaCodons;

    //Write a constructor to initialize the HashMap variable.
    public CodonCount(){
        dnaCodons = new HashMap<>();
    }

    private void resetCodonCount(){
        dnaCodons = new HashMap<>();
    }

    //A strand of DNA is made up of the symbols C, G, T, and A

    //Write a void method named buildCodonMap that has two parameters, an int named start and a
    // String named dna. This method will build a new map of codons mapped to their counts from the string dna
    // with the reading frame with the position start (a value of 0, 1, or 2). You will call this method several
    // times, so make sure your map is empty before building it.

    private void buildCodonMap(int start, String dna){
        resetCodonCount();

        int numberOfRuns = dna.length()/3;
        int starIndex = start;
        int endIndex = starIndex + 3;


        for(int i=0; i <= numberOfRuns; i++){
            if(endIndex > dna.length()){
                break;
            }
            String codon = dna.substring(starIndex, endIndex);
            boolean isKeyPresent = dnaCodons.containsKey(codon);
            if(isKeyPresent){
                int previousValue = dnaCodons.get(codon);
                dnaCodons.replace(codon, previousValue + 1);
            }
            else{
                dnaCodons.put(codon, 1);
            }
            starIndex = endIndex;
            endIndex = endIndex + 3;
        }
    }

    //Write a method named getMostCommonCodon that has no parameters. This method returns a String, the codon in a
    // reading frame that has the largest count. If there are several such codons, return any one of them.
    // This method assumes the HashMap of codons to counts has already been built.

    private String getMostCommonCodon(){
        ArrayList<Integer> codonsValues = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: dnaCodons.entrySet()){
            codonsValues.add(entry.getValue());
        }


        Collections.sort(codonsValues, Collections.reverseOrder());

        int maxValue = codonsValues.get(0);
        String maxCodon = dnaCodons.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), maxValue)).findFirst().get().getKey();
        return maxCodon;
    }

    //Write a void method named printCodonCounts that has two int parameters, start and end.
    // This method prints all the codons in the HashMap along with their counts if their count is between start and end,
    // inclusive.

    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between "+ start +" and "+ end +" inclusive are: ");
        for (Map.Entry<String, Integer> entry : dnaCodons.entrySet()) {
            int codonCount = entry.getValue();
            if (codonCount >= start && codonCount <= end) {
                System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            }
        }
    }

    //Write a tester method that prompts the user for a file that contains a DNA strand (could be upper or lower case letters
    // in the file, convert them all to uppercase, since case should not matter). Then for each of the three possible reading
    // frames, this method builds a HashMap of codons to their number of occurrences in the DNA strand, prints the total number
    // of unique codons in the reading frame, prints the most common codon and its count, and prints the codons and their number
    // of occurrences for those codons whose number of occurrences in this reading frame are between two numbers inclusive.

    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        System.out.print("the dna string is "+dna);
        for(int i=0; i <= 2; i++){
            buildCodonMap(i, dna);
            System.out.println("run number "+ i +" the total number of codons is " + dnaCodons.keySet().size());
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("The most common codon is "+ mostCommonCodon + " and it`s value is "+dnaCodons.get(mostCommonCodon));
            printCodonCounts(1,5);
            System.out.println("");
        }

    }

}

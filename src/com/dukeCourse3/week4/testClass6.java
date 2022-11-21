package com.dukeCourse3.week1;

import com.dukeCourse3.week4.*;
import edu.duke.FileResource;

import java.io.File;
import java.util.Arrays;

public class testClass6 {

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("week4Data/athens_keyflute.txt");
        int[]  response = vb.tryKeyLength(fr.toString(),5,'e');
        System.out.println("response value " + Arrays.toString(response));

    }
}

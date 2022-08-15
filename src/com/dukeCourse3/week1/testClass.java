package com.dukeCourse3.week1;

import com.dukeCourse3.week1.*;
import com.dukeCourse3.week1.CaesarCipherBest;
import com.dukeCourse3.week1.WordLengths;
import edu.duke.FileResource;

public class testClass {

    public static void main(String[] args) {
//        TestCaesarCipherTwo classToBeTested = new TestCaesarCipherTwo();
//        classToBeTested.simpleTests();

        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket";
        CaesarCipherBest cc = new CaesarCipherBest(15);
        String encryptedMessage = cc.encrypt(message);
        System.out.println("encryptedMessage one key, value " + encryptedMessage + "\n");
        CaesarCipherTwo cct = new CaesarCipherTwo(21,8);
        String encryptedMessageTwoKeys = cct.encrypt(message);
        System.out.println("encryptedMessageTwoKeys value " + encryptedMessageTwoKeys);

        WordLengths wl = new WordLengths();
        System.out.println("common word length errors.txt file");
        wl.testCountWordLengths("ProgrammingBreakingCaesarData/errors.txt");
        System.out.println("common word length manywords.txt file");
        wl.testCountWordLengths("ProgrammingBreakingCaesarData/manywords.txt");

        System.out.println("breaking the Caesar Cipher");
        CaesarCipherTwo cct2 = new CaesarCipherTwo(14,24);
        String unencryptedMessage = cct2.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy");
        System.out.println("using breaking the caesar cipher value " + unencryptedMessage);

        System.out.println("breaking the Caesar Cipher take 2");
        TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
        String unencryptedMessage2 = tcct.breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx");
        System.out.println("using breaking the caesar cipher value " + unencryptedMessage2);

        System.out.println("trying to decrypted the mystery two keys file");
        FileResource fr = new FileResource("ProgrammingBreakingCaesarData/mysteryTwoKeysQuiz.txt");
        String contents = fr.asString();
        String unencryptedMessage3 = tcct.breakCaesarCipher(contents);
        System.out.println("using breaking the caesar cipher value " + unencryptedMessage3);

        System.out.println("checking the Simple class");
        TestSimple ts = new TestSimple();
        ts.print();



    }
}

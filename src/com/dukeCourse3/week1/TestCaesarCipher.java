package com.dukeCourse3.week1;

import edu.duke.FileResource;
import com.dukeCourse3.week1.CaesarCipherHelper;

public class TestCaesarCipher {
    private CaesarCipherHelper cch = new CaesarCipherHelper();
    String alphabet = cch.alphabet;

    public String decrypt1Key (String encrypted){
        int dkey = cch.getEncryptionKey(encrypted);
        CaesarCipherBest cc = new CaesarCipherBest(dkey);
        return cc.decrypt(encrypted);
    }

    public void simpleTests(){
        FileResource fr = new FileResource();
        String contents = fr.asString();
        CaesarCipherBest cc = new CaesarCipherBest(18);
        String encrypted_message = cc.encrypt(contents);
        System.out.println("the encrypted message is " + encrypted_message);
        String decrypted_message = cc.decrypt(encrypted_message);
        System.out.println("the decrypted message is " + decrypted_message + " \n");
        System.out.println("--------------------------------------------------------");
        String decrypted_message_from_breakCaesarCipher = breakCaesarCipher(encrypted_message);
        System.out.println("decrypted_message_from_breakCaesarCipher value " + decrypted_message_from_breakCaesarCipher);
    }

    public String breakCaesarCipher(String input){
        return decrypt1Key(input);
    }
}

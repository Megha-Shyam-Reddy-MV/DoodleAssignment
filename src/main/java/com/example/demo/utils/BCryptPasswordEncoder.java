package com.example.demo.utils;

import com.example.demo.PasswordEncoder;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class BCryptPasswordEncoder extends PasswordEncoder {

    @Override
    public String encode(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keygenerator
                = KeyGenerator.getInstance(s);
        SecretKey myDesKey = keygenerator.generateKey();
        Cipher desCipher;
        desCipher = Cipher.getInstance(s);
        byte[] text = s.getBytes("UTF8");
        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        byte[] textEncrypted = desCipher.doFinal(text);
        String encoded = textEncrypted.toString();
        return encoded;
    }

    @Override
    public String decode(String encoded) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keygenerator
                = KeyGenerator.getInstance(encoded);
        SecretKey myDesKey = keygenerator.generateKey();
        Cipher desCipher = null;
        // Decrypting text
        desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        byte[] textDecrypted
                = desCipher.doFinal(encode(encoded).getBytes());

        // Converting decrypted byte array to string
        return new String(textDecrypted);
    }
    @Override
    public boolean matches(String a , String b){
        if(a == b){
            return true;
        }
        else return false;
    }
}

//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi;

import Aura.Shared.Util.BCrypt;
import CS2JNet.System.Text.EncodingSupport;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Handles hashing of passwords.
* 
* Up until G15 the client was sending plain text passwords.
* Then they would get hashed with MD5 before sending them to the server.
* In G18 KR changed this again and is now sending passwords that are
* first hashed with MD5 and then with SHA256.
* To top it of, Aura hashes what comes from the client with BCrypt.
*/
public class Password   
{
    /**
    * Lower = Speedier login, Higher = More secure
    */
    private static final int BCryptStrength = 12;
    private static MD5 _md5 = MD5.Create();
    private static SHA256Managed _sha256 = new SHA256Managed();
    /**
    * Hashes password bin with MD5.
    */
    public static String rawToMD5(byte[] passbin) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ password = passbin.TakeWhile(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(a) => {
            return a != 0;
        }" */).Aggregate("", /* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(current, chr) => {
            return current + (char)chr;
        }" */);
        return RawToMD5(password);
    }

    /**
    * Hashes password with MD5.
    */
    public static String rawToMD5(String password) throws Exception {
        return BitConverter.ToString(_md5.ComputeHash(EncodingSupport.GetEncoder("UTF-8").getBytes(password))).Replace("-", "");
    }

    /**
    * Hashes password with SHA256.
    */
    public static String mD5ToSHA256(String password) throws Exception {
        return BitConverter.ToString(_sha256.ComputeHash(EncodingSupport.GetEncoder("UTF-8").getBytes(password))).Replace("-", "");
    }

    /**
    * Hashes password first with MD5, then with SHA256.
    */
    public static String rawToMD5SHA256(String password) throws Exception {
        return mD5ToSHA256(rawToMD5(password));
    }

    /**
    * Hashes password coming from the client with BCrypt.
    */
    public static String hash(String password) throws Exception {
        return BCrypt.hashPassword(password,BCrypt.generateSalt(BCryptStrength));
    }

    /**
    * Hashes raw password with MD5, SHA256, and BCrypt.
    */
    public static String hashRaw(String password) throws Exception {
        return BCrypt.hashPassword(rawToMD5SHA256(password),BCrypt.generateSalt(BCryptStrength));
    }

    /**
    * Checks the password with BCrypt.
    */
    public static boolean check(String password, String hashedPassword) throws Exception {
        return BCrypt.checkPassword(password,hashedPassword);
    }

}



package com.ufs.sicaa.util;

import android.util.Base64;
import android.util.Log;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by guilhermeboroni on 18/04/2017.
 */

public class Security {
    public static String encrypt(String paramString)
    {
        int i = new Random().nextInt(15);
        return Integer.toHexString(i) + encrypt_string(paramString, getKey(i));
    }

    public static String encrypt_string(String paramString1, String paramString2)
    {
        Object localObject = null;
        try
        {
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString2.getBytes(), "AES");
            Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            localCipher.init(1, localSecretKeySpec);
            byte[] arrayOfByte = localCipher.doFinal(paramString1.getBytes());
            localObject = arrayOfByte;
        }
        catch (Exception localException)
        {
            for (;;)
            {
                Log.e("UfsPlus", "Erro no crypt: ", localException);
                System.out.println(localException.toString());
            }
        }
        return Base64.encodeToString((byte[])localObject, 0);
    }

    public static String getKey(int paramInt)
    {
        String[] arrayOfString = new String[16];
        arrayOfString[0] = "fnXPI6lGQvqNOhBC";
        arrayOfString[1] = "Kd1gCZMnwwSKhZjx";
        arrayOfString[2] = "DfGQBmInoRbR3Ljs";
        arrayOfString[3] = "clcNFqRHCfZsQ7Gk";
        arrayOfString[4] = "nhyVoBKY1XPFn1hP";
        arrayOfString[5] = "4J9nH06J6c20Ltx0";
        arrayOfString[6] = "YImPkFWakaQLAREX";
        arrayOfString[7] = "Ik0yvYLZLCnyajJ1";
        arrayOfString[8] = "QBsiGkmD0B4Mm4dW";
        arrayOfString[9] = "SNVjH2UOgdLSWrZz";
        arrayOfString[10] = "M2XznC0VkydlOi7w";
        arrayOfString[11] = "6ZeHC7GH2cutyoG0";
        arrayOfString[12] = "YSbGxBatkAA6M8ju";
        arrayOfString[13] = "nTudzJmTOCZkcLw7";
        arrayOfString[14] = "CLC1ZzxFAUN9CPnC";
        arrayOfString[15] = "jY1X7sXj71FMz0HX";
        return arrayOfString[paramInt];
    }
}


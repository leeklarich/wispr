import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class Encryptor {
    private String key;

    public Encryptor(String k) {
        this.key = k;
    }

    public byte[] encrypt(byte[] input) {
        byte[] encStr;

        try {
            SecretKeySpec sks = new SecretKeySpec(this.key.getBytes(), "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encStr = c.doFinal(input);
            System.out.println(encStr.length);
            return encStr;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public byte[] decrypt(byte[] input) {
        byte[] encStr;

        try {
            SecretKeySpec sks = new SecretKeySpec(this.key.getBytes(), "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            System.out.println(input.length);
            encStr = c.doFinal(input);
            return encStr;
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + input.length);
        }

        return input;
    }
}

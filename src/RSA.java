import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class RSA 
    {
        private BigInteger p;
        private BigInteger q;
        private BigInteger N;
        private BigInteger phi;
        private BigInteger e;
        private BigInteger d;
        private int        bitlength = 1024;
        private Random     r;
        public String test;
        private static final String FILENAME = "rsa_test.txt";
        BufferedWriter bw = null;
        FileWriter fw = null;
 
    public RSA(String test)
    {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
    
    public void run(String test)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;
        RSA rsa = new RSA (test);
        System.out.println("Encrypting String: " + test);
        System.out.println("String in Bytes: "
                + bytesToString(test.getBytes()));
        
        try {
            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            // encrypt
            byte[] encrypted = rsa.encrypt(test.getBytes());
            // decrypt
            byte[] decrypted = rsa.decrypt(encrypted);
            System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
            System.out.println("Decrypted String: " + new String(decrypted));
            bw.write(bytesToString(decrypted));
            bw.newLine();
            bw.write(new String(decrypted));
            bw.newLine();
            bw.close();
            fw.close();
             } catch (IOException e){
                 e.printStackTrace();
             }
    }
    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
 
    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}
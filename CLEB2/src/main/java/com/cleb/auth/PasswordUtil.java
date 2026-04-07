package com.cleb.auth;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordUtil {

    private static final int ITERATIONS  = 310_000; // how many times the hash is running
    private static final int KEY_LENGTH  = 256; // bits
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    
    
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    
    public static String hashPassword(String plainPassword, String saltBase64) {
        try {
            byte[] salt = Base64.getDecoder().decode(saltBase64);
            PBEKeySpec spec = new PBEKeySpec(
                plainPassword.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
            );
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            spec.clearPassword(); // wipe password from memory
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
   
    
    public static boolean verifyPassword(String plainPassword,
                                          String saltBase64,
                                          String storedHash) {
        String attemptHash = hashPassword(plainPassword, saltBase64);
        return attemptHash.equals(storedHash);
    }
}

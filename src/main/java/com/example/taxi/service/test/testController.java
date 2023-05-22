package com.example.taxi.service.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping("/")
    public void test() throws Exception {
        SecretKey symmetricKey = generateSymmetricKey();
        KeyPair keyPair = generateKeyPair();
        System.out.println("시작시간");
        System.out.println(LocalDateTime.now());
        String originalData = "1q2w3e4r~!";
        String encryptedData1 = encryptWithSymmetricKey(originalData, symmetricKey);
        String encryptedData2 = encryptWithPublicKey(encryptedData1, keyPair);

        System.out.println("Original Data: " + originalData);
        System.out.println("SecretKey: " + symmetricKey);
        System.out.println("Encrypted Data (Single Encryption): " + encryptedData1);
        System.out.println("keyPair: " + keyPair.getPublic());
        System.out.println("Encrypted Data (Double Encryption): " + encryptedData2);

        System.out.println("종료시간");
        System.out.println(LocalDateTime.now());
        String decryptedData1 = decryptWithPrivateKey(encryptedData2, keyPair);
        String decryptedData2 = decryptWithSymmetricKey(decryptedData1, symmetricKey);


        System.out.println("SecretKey: " + symmetricKey);
        System.out.println("Decrypted Data (Single Encryption): " + decryptedData1);
        System.out.println("keyPair: " + keyPair.getPrivate());
        System.out.println("Decrypted Data (Double Encryption): " + decryptedData2);
    }

    private static SecretKey generateSymmetricKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // 대칭키로 암호화
    private static String encryptWithSymmetricKey(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 공개키로 암호화
    private static String encryptWithPublicKey(String data, KeyPair keyPair) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 개인키로 복호화
    private static String decryptWithPrivateKey(String data, KeyPair keyPair) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] encryptedBytes = Base64.getDecoder().decode(data);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    // 대칭키로 복호화
    private static String decryptWithSymmetricKey(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedBytes = Base64.getDecoder().decode(data);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

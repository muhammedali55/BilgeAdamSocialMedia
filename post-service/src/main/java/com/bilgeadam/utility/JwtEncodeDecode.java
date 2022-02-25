package com.bilgeadam.utility;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class JwtEncodeDecode {

    private String privateurl= "C:\\SIFRE\\private.key";

    private String publicurl= "C:\\SIFRE\\public.key";

    public String getEncryptUUID(String uuid){
        try{
            String secretMessage = uuid;
            File publicKeyFile = new File(publicurl);
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
            String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
            return encodedMessage;
        }catch (NoSuchAlgorithmException exception){
            return null;
        }catch (NoSuchPaddingException exception){
            return null;
        }catch (InvalidKeyException exception){
            return null;
        }catch (IllegalBlockSizeException exception){
            return null;
        }catch (BadPaddingException exception){
            return null;
        }catch (IOException exception){
            return null;
        }catch (Exception exception){
            return null;
        }

    }

    public String getDecodeUUID(String secretValue) {
        try{
            String encodedMessage = secretValue.toString();
            File privateKeyFile = new File(privateurl);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] messageBytes = Base64.getDecoder().decode(encodedMessage);
            byte[] decryptedMessageBytes = decryptCipher.doFinal(messageBytes);
            String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
            return decryptedMessage;
        }catch (NoSuchAlgorithmException exception){
            return null;
        }catch (NoSuchPaddingException exception){
            return null;
        }catch (InvalidKeyException exception){
            return null;
        }catch (IllegalBlockSizeException exception){
            return null;
        }catch (BadPaddingException exception){
            return null;
        }catch (IOException exception){
            return null;
        }catch (Exception exception){
            return null;
        }

    }

}

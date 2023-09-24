package com.core.shared.Classes.CryptoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.Provider;
import java.security.Security;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.core.shared.Abstructions.CryptoManager.ICryptoManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

public class SymatricCryptoManager extends ICryptoManager{
    private ILoggerManager logger;
    private Cipher cipher;
    private IvParameterSpec iv;
    private SecretKey secretKey;
    private CryptoConfig config;

    public SymatricCryptoManager(ILoggerManager logger,CryptoConfig config){
        this.logger=logger;
        this.config=config;
        try{
            this.cipher = Cipher.getInstance(config.getAlgorithm());
            this.secretKey = generaSecretKey(config.getPassword(),config.getSalt());
            this.iv=generateIv();
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
    }

    private IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        iv = "1234567890123456".getBytes();
        return new IvParameterSpec(iv);
    }
    
    private SecretKey generaSecretKey(String key,String salt){
        try{
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(),200, config.getKeySize());
            SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), config.getEngine());
            return secret;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }
   
    
    @Override
    public byte[] encrypt(byte[] plainText){
        try{
            this.cipher.init(Cipher.ENCRYPT_MODE,secretKey,iv);
            byte[] cipherText = cipher.doFinal(plainText);
            //return Base64.getEncoder().encodeToString(cipherText);
            return cipherText;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }
    @Override
    public SealedObject encryptObject(Serializable object){
        try{
            this.cipher.init(Cipher.ENCRYPT_MODE,secretKey,iv);
            SealedObject sealedObject = new SealedObject(object, cipher);
            return sealedObject;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {
        try{
            this.cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
            byte[] plainText = cipher.doFinal(cipherText);
            return plainText;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }
    @Override
    public Serializable decryptObject(SealedObject sealedObject) {
        try{
            this.cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
            Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
            return unsealObject;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }
    @Override
    public File encryptFile(File inputFile,File outputFile) {
        try{
            this.cipher.init(Cipher.ENCRYPT_MODE,secretKey,iv);
            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
            inputStream.close();
            outputStream.close();
            return outputFile;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }
    @Override
    public File decryptFile(File inputFile,File outputFile) {
        try{
            this.cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
            inputStream.close();
            outputStream.close();
            return outputFile;
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }
    


}

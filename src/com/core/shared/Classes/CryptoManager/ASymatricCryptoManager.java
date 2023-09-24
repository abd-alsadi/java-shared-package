package com.core.shared.Classes.CryptoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.*;

import com.core.shared.Abstructions.CryptoManager.ICryptoManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

public class ASymatricCryptoManager extends ICryptoManager{
    private ILoggerManager logger;
    private Cipher cipher;
    private CryptoConfig config;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public ASymatricCryptoManager(ILoggerManager logger,CryptoConfig config){
        this.logger=logger;
        this.config=config;
        try{
            this.privateKey = generatePrivateKey(config.getPrivateKey());
            this.publicKey = generatePublicKey(config.getPublicKey());
            this.cipher = Cipher.getInstance(config.getAlgorithm());
        }catch(Exception e){
            if(this.logger!=null)
            this.logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
    }

    
    private  PublicKey generatePublicKey(byte[] key){
        try{
            KeyFactory keyFactory = KeyFactory.getInstance(config.getEngine());
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(key);
            return keyFactory.generatePublic(publicKeySpec);
        }catch(Exception e){
           
        }
        return null;
    }
    private PrivateKey generatePrivateKey(byte[] key){
        try{
            KeyFactory keyFactory = KeyFactory.getInstance(config.getEngine());
            EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(key);
            return keyFactory.generatePrivate(publicKeySpec);
        }catch(Exception e){
          String error = e.getMessage();
        }
        return null;
    }
    
    @Override
    public byte[] encrypt(byte[] plainText){
        try{
            this.cipher.init(Cipher.ENCRYPT_MODE,publicKey);
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
            this.cipher.init(Cipher.ENCRYPT_MODE,publicKey);
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
            this.cipher.init(Cipher.DECRYPT_MODE,privateKey);
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
            this.cipher.init(Cipher.DECRYPT_MODE,privateKey);
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
            this.cipher.init(Cipher.ENCRYPT_MODE,publicKey);
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
            this.cipher.init(Cipher.DECRYPT_MODE,privateKey);
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

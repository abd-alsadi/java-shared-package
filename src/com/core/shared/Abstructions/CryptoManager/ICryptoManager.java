package com.core.shared.Abstructions.CryptoManager;

import java.io.File;
import java.io.Serializable;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

public abstract class ICryptoManager{
    public abstract byte[] encrypt(byte[] data);
    public abstract byte[] decrypt(byte[] data);
    public abstract SealedObject encryptObject(Serializable object);
    public abstract Serializable decryptObject(SealedObject sealedObject);
    public abstract File encryptFile(File inputFile,File outputFile);
    public abstract File decryptFile(File inputFile,File outputFile);


    public static List<String> getListAlgorithms() {
        Stream<Provider> providers = Arrays.stream(Security.getProviders());
        List<String> algorithms = providers
        .flatMap(provider -> provider.getServices().stream())
        .filter(service -> "Cipher".equals(service.getType()))
        .map(Provider.Service::getAlgorithm)
        .collect(Collectors.toList());
        return algorithms;
    }

    public static SecretKey generateSymatricRandomKey(int length,String engine) {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance(engine);
            keyGenerator.init(length);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey;
        }catch(Exception e){
           
        }
        return null;
    }
    public static KeyPair generateASymatricRandomKey(int length,String engine) {
        try{
            KeyPairGenerator generator = KeyPairGenerator.getInstance(engine);
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();
            return pair;
        }catch(Exception e){
           
        }
        return null;
    }
   
}

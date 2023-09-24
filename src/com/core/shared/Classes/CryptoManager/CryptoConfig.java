package com.core.shared.Classes.CryptoManager;

import java.security.PrivateKey;
import java.security.PublicKey;

public class CryptoConfig {
    private String password;
    private String salt;
    private String algorithm;
    private String engine;
    private int keySize;
    private byte[] privateKey;
    private byte[] publicKey;
    public CryptoConfig(String algorithm,String engine,String password,String salt,int keySize){
        this.algorithm=algorithm;
        this.password=password;
        this.salt = salt;
        this.engine=engine;
        this.keySize=keySize;
    }
    public CryptoConfig(String algorithm,String engine,byte[] privateKey,byte[] publicKey){
        this.algorithm=algorithm;
        this.publicKey=publicKey;
        this.privateKey = privateKey;
        this.engine=engine;
    }
    public int getKeySize(){
        return this.keySize;
    }
    public String getPassword(){
        return password;
    }
    public String getSalt(){
        return salt;
    }
    public String getAlgorithm(){
        return algorithm;
    }
    public String getEngine(){
        return this.engine;
    }
    public byte[] getPrivateKey(){
        return privateKey;
    }
    public byte[] getPublicKey(){
        return publicKey;
    }
}

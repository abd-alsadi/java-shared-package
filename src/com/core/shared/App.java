package com.core.shared;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.core.shared.Abstructions.CacheManager.ICacheManager;
import com.core.shared.Abstructions.CryptoManager.ICryptoManager;
import com.core.shared.Abstructions.DBManager.IDBManager;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilder;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilderBase;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilderDelete;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilderInsert;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilderSelect;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilderUpdate;
import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilder.*;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.CacheManager.MemoryCacheManager;
import com.core.shared.Classes.CryptoManager.SymatricCryptoManager;
import com.core.shared.Classes.CryptoManager.ASymatricCryptoManager;
import com.core.shared.Classes.CryptoManager.CryptoConfig;
import com.core.shared.Classes.DBManager.DBManagerConnection;
import com.core.shared.Classes.DBManager.DBPostresManager;
import com.core.shared.Classes.DBQueryBuilder.DBSqlQueryBuilder;
import com.core.shared.Classes.LoggerManager.FileLoggerManager;
import com.core.shared.Enums.DBQueryConditionOpEnum;
import com.core.shared.Enums.DBQuerySortEnum;

public class App {
    public static void main(String[] args) throws Exception {
       
        // IDBQueryBuilderBase xx = new SqlQueryBuilder();
        // IDBQueryBuilderSelect bSelect =xx.BuildSelectStatement();
        // String selectQuery = bSelect
        // .From("Student")
        // .SelectDistinct()
        // .Limit(2)
        // .BuildSelectQuery();
        
        // IDBQueryBuilderSelect bSelect2 =xx.BuildSelectStatement();
        // String selectQuery2 = bSelect
        // .From(bSelect,"x")
        // .SelectDistinct()
        // .Limit(2)
        // .BuildSelectQuery();
        // System.out.println("select : \n"+selectQuery2);
       
        // IDBQueryBuilderInsert bInsert =xx.BuildInsertStatement();
        // String insertQuery = bInsert
        // .InsertTable("Student")
        // .Insert(new String[]{"col1","col2"}, new Object[]{"1",2})
        // .BuildInsertQuery();
        // System.out.println("insert : \n"+insertQuery);

        // IDBQueryBuilderUpdate bUpdate = xx.BuildUpdateStatement();
        // String updateQuery = bUpdate
        // .UpdateTable("Student")
        // .Update(new String[]{"col1","col2"}, new Object[]{"1",2})
        // .BuildUpdateQuery();
        // System.out.println("update : \n"+updateQuery);

        // IDBQueryBuilderDelete bDelete =xx.BuildDeleteStatement();
        // String deleteQuery = bDelete
        // .DeleteTable("Student")
        // .DeleteWhereAnd(bSelect, DBQueryConditionOp.EQUAL,2)
        // .BuildDeleteQuery(); 
        // System.out.println("delete : \n"+deleteQuery);
 
       // DBManagerConnection connectionSql = new DBManagerConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/sonoo", "root", "root");
       DBManagerConnection connectionPostgres =
       new DBManagerConnection("jdbc:postgresql://localhost:5432/authdb?ssl=false", "postgres", "postgres");
 
       ILoggerManager logger = new FileLoggerManager("log.txt");
       //IDBManager dbManager= new DBPostresManager(logger,connectionPostgres,false);
        // ResultSet result = dbManager.Select("select * from role");
        // if(result!=null){
        //     result.next();
        //     String xx = result.getString("role_$$$_client_id");
        //     System.out.println(xx);
        // }
        // IDBQueryBuilderInsert queryBuilder = new DBSqlQueryBuilder();
        // String query = queryBuilder.insertTable("public.role").insert(new String[]{"role_id","role_name"}, new Object[]{100,"abd"}).buildInsertQuery();
    
        // int resultxx = dbManager.insert(query);
        KeyPair pair = ICryptoManager.generateASymatricRandomKey(0, "RSA");
        byte[] privateKey =pair.getPrivate().getEncoded();
        byte[] publicKey =pair.getPublic().getEncoded();
        //CryptoConfig config = new CryptoConfig("AES/CBC/PKCS5Padding","AES",key,salt,128);
        CryptoConfig config = new CryptoConfig("RSA","RSA",privateKey,publicKey);

        ICryptoManager vrypto = new ASymatricCryptoManager(logger,config);
        byte[] data = vrypto.encrypt("kaka".getBytes());
        byte[] de=vrypto.decrypt(data);
        System.out.println(new String(de));

        // String group="group1";
        // String key = "key1";
        // String value = "val1";
        // ICacheManager cache = MemoryCacheManager.getInstance().init(logger);
        // cache.put(group, key, value);
        // String vv = (String)cache.get(group,key);
        // cache.delete(group, key);
        // String vv2 = (String)cache.get(group,key);
        // System.out.println(vv);
        // System.out.println(vv2);

        System.out.println("-------------------------------");
 
    }
}

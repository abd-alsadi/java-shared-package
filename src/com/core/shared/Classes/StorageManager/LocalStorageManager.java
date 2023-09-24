package com.core.shared.Classes.StorageManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Abstructions.StorageManager.IStorageManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

public class LocalStorageManager implements IStorageManager {

    private ILoggerManager logger;
    private String connectionString;
    public LocalStorageManager(ILoggerManager logger,String connectionString){
        this.logger= logger;
        this.connectionString=connectionString;
    }

    @Override
    public String createFile(String path,byte[] data) {
        try{
            File file = new File(path);
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
            return path;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return "";
    }
    @Override
    public String updateFile(String path,byte[] data) {
        try{
            deleteFile(path);
            File file = new File(path);
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
            return path;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return "";
    }
    @Override
    public boolean isExistsFile(String path) {
        try{
            File file = new File(path);
            return file.exists();
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
    }

    @Override
    public byte[] getFile(String path) {
        try{
            File file = new File(path);
            FileInputStream out = new FileInputStream(file);
            byte[] result = out.readAllBytes();
            out.close();
            return result;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }

    @Override
    public boolean deleteFile(String path) {
        try{
            File file = new File(path);
            return file.delete();
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
    }
    
}

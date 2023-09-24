package com.core.shared.Classes.LoggerManager;

import com.core.shared.Enums.LoggerMessageTypeEnum;

public class LoggerMessage {
    private String message;
    private String packageName;
    private LoggerMessageTypeEnum type;
    public LoggerMessage(String packageName,String message,LoggerMessageTypeEnum type){
        this.type=type;
        this.packageName=packageName;
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
    public LoggerMessageTypeEnum getType(){
        return this.type;
    }
    public String getPackage(){
        return this.packageName;
    }
}

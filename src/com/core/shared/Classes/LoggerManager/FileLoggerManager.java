package com.core.shared.Classes.LoggerManager;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
public class FileLoggerManager implements ILoggerManager{
    private String connectionString;
    public FileLoggerManager(String connectionString){
        this.connectionString=connectionString;
    }

    private void initSource(String connectionString){
        try{
            File file = new File(connectionString);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch(Exception e){
            String error = e.getMessage();
        }
    }
    private String getPackageName(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String result = stackTrace[stackTrace.length-1].getClassName()+":"+stackTrace[stackTrace.length-1].getMethodName();
        return result;
    }
    private String convertMessageToString(LoggerMessage message){
        String pattern = "{DATE},{PACKAGE},{MSG},{TYPE}";
        pattern=pattern.replace("{DATE}",LocalDate.now().toString());
        if(message.getPackage()!=null && !message.getPackage().isEmpty())
            pattern=pattern.replace("{PACKAGE}",message.getPackage());
        else
            pattern=pattern.replace("{PACKAGE}",getPackageName());
            
        pattern=pattern.replace("{MSG}",message.getMessage());

        String type = "";
        switch(message.getType()){
            case ERROR :
                type="ERROR";
            break;
            case EXCEPTION :
                type="EXCEPTION";
            break;
            case INFO :
                type="INFO";
            break;
            default :
                type="INFO";
            break;
        }
        pattern=pattern.replace("{TYPE}",type);
        return pattern;
    }
    @Override
    public void log(LoggerMessage message) {
        try{
            initSource(this.connectionString);
            FileWriter out = new FileWriter(connectionString, true);
            String msg = convertMessageToString(message);
            out.write(msg);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

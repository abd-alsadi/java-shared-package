package com.core.shared.Factories;

import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.FileLoggerManager;
import com.core.shared.Enums.LoggerManagerTypeEnum;

public class LoggerManagerFactory {
    public ILoggerManager createLoggerManager(LoggerManagerTypeEnum type,String connectionString){
        ILoggerManager instance;
        switch(type){
            case FILE :
                instance = new FileLoggerManager(connectionString);
            break;
            default :
                instance = new FileLoggerManager(connectionString);
            break;
        }
        return instance;
    }
}

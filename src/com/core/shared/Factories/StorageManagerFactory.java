package com.core.shared.Factories;

import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Abstructions.StorageManager.IStorageManager;
import com.core.shared.Classes.StorageManager.LocalStorageManager;
import com.core.shared.Enums.StorageManagerTypeEnum;

public class StorageManagerFactory {
    public IStorageManager createStorageManager(ILoggerManager logger, StorageManagerTypeEnum type,String connectionString){
        IStorageManager instance;
        switch(type){
            case LOCAL :
                instance = new LocalStorageManager(logger,connectionString);
            break;
            default :
                instance = new LocalStorageManager(logger,connectionString);
            break;
        }
        return instance;
    }
}

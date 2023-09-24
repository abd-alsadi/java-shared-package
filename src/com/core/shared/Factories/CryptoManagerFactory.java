package com.core.shared.Factories;

import com.core.shared.Abstructions.CryptoManager.ICryptoManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.CryptoManager.SymatricCryptoManager;
import com.core.shared.Classes.CryptoManager.ASymatricCryptoManager;
import com.core.shared.Classes.CryptoManager.CryptoConfig;
import com.core.shared.Enums.CryptoManagerTypeEnum;

public class CryptoManagerFactory {
    public ICryptoManager createStorageManager(ILoggerManager logger, CryptoManagerTypeEnum type,CryptoConfig config){
        ICryptoManager instance;
        switch(type){
            case SYMATRIC :
                instance = new SymatricCryptoManager(logger,config);
            break;
            case ASYMATRIC :
                    instance = new ASymatricCryptoManager(logger,config);
                break;
            default :
            instance = new SymatricCryptoManager(logger,config);
            break;
        }
        return instance;
    }
}

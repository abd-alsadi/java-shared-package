package com.core.shared.Factories;

import com.core.shared.Abstructions.DBManager.IDBManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.DBManager.DBManagerConnection;
import com.core.shared.Classes.DBManager.DBPostresManager;
import com.core.shared.Enums.DBManagerTypeEnum;

public class DBManagerFactory {
    public IDBManager createDBManager(ILoggerManager logger, DBManagerTypeEnum type,DBManagerConnection conn,boolean withTransaction){
        IDBManager instance;
        switch(type){
            case POSTGRES :
                instance = new DBPostresManager(logger,conn, withTransaction);
            break;
            default :
                instance = new DBPostresManager(logger,conn, withTransaction);
        break;
        }
        return instance;
    }
}

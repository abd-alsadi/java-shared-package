package com.core.shared.Factories;

import com.core.shared.Abstructions.DBQueryBuilder.IDBQueryBuilder;
import com.core.shared.Classes.DBQueryBuilder.DBSqlQueryBuilder;
import com.core.shared.Enums.DBQueryBuilderTypeEnum;

public class DBQueryBuilderFactory {
    public IDBQueryBuilder createDBQueryBuilder(DBQueryBuilderTypeEnum type){
        IDBQueryBuilder instance;
        switch(type){
            case SQL :
                instance = new DBSqlQueryBuilder();
            break;
            default :
                instance = new DBSqlQueryBuilder();
            break;
        }
        return instance;
    }
}

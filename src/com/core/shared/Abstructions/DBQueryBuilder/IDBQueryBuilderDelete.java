package com.core.shared.Abstructions.DBQueryBuilder;

import com.core.shared.Enums.DBQueryConditionOpEnum;

public interface IDBQueryBuilderDelete {
    public String buildDeleteQuery(); 
    public IDBQueryBuilderDelete deleteTable(String name);
    public IDBQueryBuilderDelete deleteWhereAnd(String column,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderDelete deleteWhereOR(String column,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderDelete deleteWhereAnd(IDBQueryBuilderSelect subQuery,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderDelete deleteWhereOR(IDBQueryBuilderSelect subQuery,DBQueryConditionOpEnum operation,Object value);

}

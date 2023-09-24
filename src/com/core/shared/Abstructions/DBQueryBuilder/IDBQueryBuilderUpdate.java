package com.core.shared.Abstructions.DBQueryBuilder;

import com.core.shared.Enums.DBQueryConditionOpEnum;

public interface IDBQueryBuilderUpdate{
    public IDBQueryBuilderUpdate update(String[] columns,Object[] data); 
    public String buildUpdateQuery(); 
    public IDBQueryBuilderUpdate updateTable(String name);
    public IDBQueryBuilderUpdate updateWhereAnd(String column,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderUpdate updateWhereOR(String column,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderUpdate updateWhereAnd(IDBQueryBuilderSelect subQuery,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderUpdate updateWhereOR(IDBQueryBuilderSelect subQuery,DBQueryConditionOpEnum operation,Object value);

}

package com.core.shared.Abstructions.DBQueryBuilder;

import com.core.shared.Enums.DBQueryConditionOpEnum;
import com.core.shared.Enums.DBQuerySortEnum;

public interface IDBQueryBuilderSelect {
    public IDBQueryBuilderSelect select(String... columns);
    public IDBQueryBuilderSelect select();

    public IDBQueryBuilderSelect selectTop(int number ,String... columns);
    public IDBQueryBuilderSelect selectTop(int number);
    public IDBQueryBuilderSelect selectDistinct(String... columns);
    public IDBQueryBuilderSelect selectDistinct();

    public IDBQueryBuilderSelect from(String table);
    public IDBQueryBuilderSelect from(IDBQueryBuilderSelect subQuery,String alias);

    public IDBQueryBuilderSelect innerJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect leftJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect rightJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect outerJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect crossJoin(IDBQueryBuilderSelect subQuery);

    public IDBQueryBuilderSelect innerJoin(String table,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect leftJoin(String table,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect rightJoin(String table,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect outerJoin(String table,String PKColumn,String FKColumn);
    public IDBQueryBuilderSelect crossJoin(String table);

    public IDBQueryBuilderSelect count(String alias);
    public IDBQueryBuilderSelect count(String column, String alias);
    public IDBQueryBuilderSelect max(String column, String alias);
    public IDBQueryBuilderSelect min(String column, String alias);
    public IDBQueryBuilderSelect sum(String column, String alias);
    public IDBQueryBuilderSelect avg(String column, String alias);

    public IDBQueryBuilderSelect limit(int number);
    public IDBQueryBuilderSelect limit(int number,int skip);

    public IDBQueryBuilderSelect union(IDBQueryBuilderSelect queryBuilder); 
    public IDBQueryBuilderSelect unionAll(IDBQueryBuilderSelect queryBuilder); 

    public abstract IDBQueryBuilderSelect orderBy(DBQuerySortEnum sort,String... columns);
    public abstract IDBQueryBuilderSelect groupBy(String... columns);
    public abstract IDBQueryBuilderSelect having(String column,DBQueryConditionOpEnum operation,Object value);
   
    public IDBQueryBuilderSelect selectWhereAnd(String column,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderSelect selectWhereOR(String column,DBQueryConditionOpEnum operation,Object value);

    public IDBQueryBuilderSelect selectWhereAnd(IDBQueryBuilderSelect subQuery,DBQueryConditionOpEnum operation,Object value);
    public IDBQueryBuilderSelect selectWhereOR(IDBQueryBuilderSelect subQuery,DBQueryConditionOpEnum operation,Object value);

    public  String buildSelectQuery(); 
}

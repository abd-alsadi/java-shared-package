package com.core.shared.Abstructions.DBQueryBuilder;

public interface IDBQueryBuilderInsert  {
    public IDBQueryBuilderInsert insert(String[] columns,Object[] data); 
    public String buildInsertQuery(); 
    public IDBQueryBuilderInsert insertTable(String name);

}

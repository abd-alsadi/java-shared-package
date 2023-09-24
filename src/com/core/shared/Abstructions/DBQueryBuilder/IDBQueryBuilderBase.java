package com.core.shared.Abstructions.DBQueryBuilder;

public abstract class IDBQueryBuilderBase {
    public abstract IDBQueryBuilderSelect buildSelectStatement();
    public abstract IDBQueryBuilderInsert buildInsertStatement();
    public abstract IDBQueryBuilderUpdate buildUpdateStatement();
    public abstract IDBQueryBuilderDelete buildDeleteStatement();
}

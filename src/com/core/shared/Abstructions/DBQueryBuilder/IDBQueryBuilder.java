package com.core.shared.Abstructions.DBQueryBuilder;

import java.util.List;

public abstract class IDBQueryBuilder extends IDBQueryBuilderBase 
                                      implements Cloneable,
                      IDBQueryBuilderSelect,
                      IDBQueryBuilderInsert,
                      IDBQueryBuilderUpdate,
                      IDBQueryBuilderDelete{
    protected String insertQuery;
    protected String deleteQuery;
    protected String updateQuery;
    protected String tableQuery;
    protected String selectQuery;
    protected String whereQuery;
    protected String sortQuery;
    protected String havingQuery;
    protected String groupQuery;
    protected String fromQuery;
    protected String limitQuery;
    

    protected List<IDBQueryBuilderSelect> unionList;
    protected List<String> unionListKeys;
    
    protected IDBQueryBuilder(){
      this.reset();
    }

    protected IDBQueryBuilder(IDBQueryBuilder builder){
        this.insertQuery=builder.insertQuery;
        this.deleteQuery=builder.deleteQuery;
        this.updateQuery=builder.updateQuery;
        this.tableQuery=builder.tableQuery;
        this.selectQuery=builder.selectQuery;
        this.whereQuery=builder.whereQuery;
        this.sortQuery=builder.sortQuery;
        this.havingQuery=builder.havingQuery;
        this.groupQuery=builder.groupQuery;
        this.fromQuery=builder.fromQuery;
        this.limitQuery=builder.limitQuery;
        
        if(builder.unionList!=null)
        this.unionList.addAll(builder.unionList);

        if(builder.unionListKeys!=null)
        this.unionListKeys.addAll(builder.unionListKeys);
    }

    public abstract IDBQueryBuilder reset();
    public abstract IDBQueryBuilder get();
}

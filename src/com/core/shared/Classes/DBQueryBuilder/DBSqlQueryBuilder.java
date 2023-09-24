package com.core.shared.Classes.DBQueryBuilder;

import java.util.ArrayList;
import java.util.List;

import com.core.shared.Abstructions.DBQueryBuilder.*;
import com.core.shared.Enums.DBQueryConditionOpEnum;
import com.core.shared.Enums.DBQuerySortEnum;

public class DBSqlQueryBuilder extends IDBQueryBuilder{

    public DBSqlQueryBuilder(){
        super();
    }
    public static IDBQueryBuilder builder(){
        return new DBSqlQueryBuilder();
    }
    public DBSqlQueryBuilder(IDBQueryBuilder builder){
        super(builder);
    }

    @Override
    public IDBQueryBuilderSelect buildSelectStatement(){
        return new DBSqlQueryBuilder();
    }

    @Override
    public IDBQueryBuilderInsert buildInsertStatement(){
        return new DBSqlQueryBuilder();
    }
    @Override
    public IDBQueryBuilderUpdate buildUpdateStatement(){
        return new DBSqlQueryBuilder();
    }
    @Override
    public IDBQueryBuilderDelete buildDeleteStatement(){
        return new DBSqlQueryBuilder();
    }

    @Override
    public IDBQueryBuilderSelect select(String... columns) {
        if(columns==null || columns.length==0)
        return this;

        String statement ="SELECT {COLUMNS} ";
        String cols = "";
        for (String col : columns) {
            cols += col+",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);

        statement = statement.replace("{COLUMNS}", cols);
        this.selectQuery = statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect select() {
        String statement  = "SELECT * ";

        this.selectQuery = statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect from(String table) {
        if(table == null || table == "")
        return this;

        String statement = "FROM {TABLE} ";
        statement = statement.replace("{TABLE}", table);

        this.fromQuery = statement;
        return this;
    }

    public IDBQueryBuilderSelect orderBy(DBQuerySortEnum sort,String... columns) {
        if(columns==null || columns.length==0)
        return this;

        String statement  =  "ORDER BY {COLUMNS} {DIRECTION}";
        String cols = "";
        for (String col : columns) {
            cols += col + ",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);

        String dir = "";    
        if(sort == DBQuerySortEnum.ASC)    
        dir += " ASC ";
        else
        dir += " DESC "; 

        statement = statement.replace("{COLUMNS}", cols).replace("{DIRECTION}",dir);

        this.sortQuery = statement;

        return this;
    }
    @Override
    public IDBQueryBuilderSelect groupBy(String... columns) {
        if(columns==null || columns.length==0)
        return this;

        String statement  = "Group BY {COLUMNS} ";
        String cols = "";
        for (String col : columns) {
            cols += col + ",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);


        statement = statement.replace("{COLUMNS}", cols);

        this.groupQuery = statement;

        return this;
    }

    private String buildWhereAnd(String column, DBQueryConditionOpEnum operation, Object value){
        String statement  = "WHERE (({COLUMN}){OP} {VALUE}) ";
        String op = buildConditionOperation(operation);
        Object val = buildConditionValue(operation,value);
        statement = statement.replace("{COLUMN}", column);
        statement = statement.replace("{OP}",op);
        statement = statement.replace("{VALUE}",val.toString());

        if(!this.whereQuery.isEmpty() && this.whereQuery.toLowerCase().contains("where"))
            this.whereQuery += " AND ";
        else
            this.whereQuery="";

        this.whereQuery += statement;
        return  this.whereQuery;
    }
    private String buildWhereOr(String column, DBQueryConditionOpEnum operation, Object value){
        String statement  = "WHERE (({COLUMN}){OP}{VALUE}) ";
        String op = buildConditionOperation(operation);
        Object val = buildConditionValue(operation,value);
        statement = statement.replace("{COLUMN}", column).replace("{OP}",op).replace("{VALUE}",val.toString());

        if(!this.whereQuery.isEmpty() && this.whereQuery.toLowerCase().contains("where"))
            this.whereQuery += " OR ";
        else
            this.whereQuery="";

        this.whereQuery += statement;
        return  this.whereQuery;
    }
    @Override
    public IDBQueryBuilderSelect selectWhereAnd(String column, DBQueryConditionOpEnum operation, Object value) {
        this.whereQuery = buildWhereAnd(column,operation,value);
        return this;
    }     
    @Override
    public IDBQueryBuilderSelect selectWhereAnd(IDBQueryBuilderSelect subQuery, DBQueryConditionOpEnum operation, Object value) {
        if(subQuery==null)
        return this;
        String subQueryStatement = subQuery.buildSelectQuery();
        this.whereQuery = buildWhereAnd(subQueryStatement,operation,value);
        return this;
    }   
    @Override
    public IDBQueryBuilderDelete deleteWhereAnd(String column, DBQueryConditionOpEnum operation, Object value) {
        this.whereQuery = buildWhereAnd(column,operation,value);
        return this;
    }  
    @Override
    public IDBQueryBuilderUpdate updateWhereAnd(String column, DBQueryConditionOpEnum operation, Object value) {
        this.whereQuery = buildWhereAnd(column,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderUpdate updateWhereAnd(IDBQueryBuilderSelect subQuery, DBQueryConditionOpEnum operation, Object value) {
        if(subQuery==null)
        return this;
        String subQueryStatement = subQuery.buildSelectQuery();
        this.whereQuery = buildWhereAnd(subQueryStatement,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderSelect selectWhereOR(String column, DBQueryConditionOpEnum operation, Object value) {
        this.whereQuery = buildWhereOr(column,operation,value);

        return this;
    } 
    @Override
    public IDBQueryBuilderSelect selectWhereOR(IDBQueryBuilderSelect subQuery, DBQueryConditionOpEnum operation, Object value) {
        if(subQuery==null)
        return this;
        String subQueryStatement = subQuery.buildSelectQuery();
        this.whereQuery = buildWhereOr(subQueryStatement,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderUpdate updateWhereOR(IDBQueryBuilderSelect subQuery, DBQueryConditionOpEnum operation, Object value) {
        if(subQuery==null)
        return this;
        String subQueryStatement = subQuery.buildSelectQuery();
        this.whereQuery = buildWhereOr(subQueryStatement,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderDelete deleteWhereOR(IDBQueryBuilderSelect subQuery, DBQueryConditionOpEnum operation, Object value) {
        if(subQuery==null)
        return this;
        String subQueryStatement = subQuery.buildSelectQuery();
        this.whereQuery = buildWhereOr(subQueryStatement,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderDelete deleteWhereAnd(IDBQueryBuilderSelect subQuery, DBQueryConditionOpEnum operation, Object value) {
        if(subQuery==null)
        return this;
        String subQueryStatement = subQuery.buildSelectQuery();
        this.whereQuery = buildWhereAnd(subQueryStatement,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderUpdate updateWhereOR(String column, DBQueryConditionOpEnum operation, Object value) {
        this.whereQuery = buildWhereOr(column,operation,value);
        return this;
    } 
    @Override
    public IDBQueryBuilderDelete deleteWhereOR(String column, DBQueryConditionOpEnum operation, Object value) {
        this.whereQuery = buildWhereOr(column,operation,value);


        return this;
    }   

    private String buildConditionOperation(DBQueryConditionOpEnum operation){
        String op = " = ";
        switch(operation){
            case EQUAL:
                op=" = ";
            break;
            case NOT_EQUAL:
                op=" <> ";
            break;
            case GREATER_THAN:
                op=" > ";
            break;
            case LESS_THAN:
                op=" < ";
            break;
            case GREATER_EQUAL_THAN:
                 op=" >= ";
            break;
            case LESS_EQUAL_THAN:
                op=" <= ";
            break;
            case LIKE:
                op=" LIKE ";
            break;
            case NOT_LIKE:
                 op=" NOT LIKE ";
            break;
            case IN:
                op=" IN ";
            break;
            case NOT_IN:
                op=" NOT IN ";
            break;
            case BETWEEN:
                op=" BETWEEN ";
            break;
            case NOT_BETWEEN:
                op=" NOT BETWEEN ";
            break;
            case ALL:
                op=" ALL ";
            break;
            case NOT_ALL:
                op=" NOT ALL ";
            break;
            case EXISTS:
                op=" EXISTS ";
            break;
            case NOT_EXISTS:
                op=" NOT EXISTS ";
            break;
            case IS_NULL:
                op=" IS NULL ";
            break;
            case IS_NOT_NULL:
                op=" IS NOT NULL ";
            break;
            case ANY:
                op=" ANY ";
            break;
            case NOT_ANY:
                 op=" NOT ANY ";
            break;
        }
        return op;
    }

    private Object buildConditionValue(DBQueryConditionOpEnum operation,Object value){
        if(value !=null && value instanceof IDBQueryBuilder){
            value = ((IDBQueryBuilderSelect)value).buildSelectQuery();
        }
        Object val = value;

        switch(operation){
            case EQUAL,
                NOT_EQUAL,
                GREATER_THAN,
                LESS_THAN,
                GREATER_EQUAL_THAN,
                LESS_EQUAL_THAN :   
                if(value instanceof String)    
                    val = "'" +String.valueOf(value) + "'";
                else 
                    val =value.toString();
            break;
            case LIKE,NOT_LIKE:
                val = "'" +String.valueOf(value) + "'";
            break;
            case IN,NOT_IN:
                val = "(" +String.valueOf(value) + ")";
            break;
            case BETWEEN,NOT_BETWEEN:
                val="  ";
            break;
            case ALL,NOT_ALL:
                val = "(" +String.valueOf(value) + ")";
            break;
            case EXISTS,NOT_EXISTS:
            val = "(" +String.valueOf(value) + ")";
            break;
            case IS_NULL,IS_NOT_NULL:
                val = "";
            break;
            case ANY,NOT_ANY:
            val = "(" +String.valueOf(value) + ")";
            break;
        }
        return val;
    }
    @Override
    public IDBQueryBuilderSelect having(String column, DBQueryConditionOpEnum operation, Object value) {
        String statement  = "Having ({COLUMN}{OP}{VALUE}) ";
     
        String op = buildConditionOperation(operation);
        Object val = buildConditionValue(operation,value);
        statement = statement.replace("{COLUMN}", column).replace("{OP}",op).replace("{VALUE}",val.toString());

        this.havingQuery = statement;
        return this;
    }    

    @Override
    public String buildSelectQuery() {
        String statement = "{selectQuery} {fromQuery} {whereQuery} {groupQuery} {havingQuery} {sortQuery} {limitQuery}";

        String selectQ = "";
        String fromQ = "";
        String whereQ = "";
        String groupQ = "";
        String havingQ = "";
        String sortQ = "";
        String limitQ = "";
        boolean isWhere=false;
        boolean isGroup=false;
        boolean isHaving=false;
        boolean isSort=false;
        boolean isLimit=false;
        if(this.selectQuery!=null && !this.selectQuery.isEmpty())
            selectQ = this.selectQuery;

            if(this.fromQuery!=null && !this.fromQuery.isEmpty())
            fromQ = this.fromQuery;

            if(this.whereQuery!=null && !this.whereQuery.isEmpty()){
                whereQ = this.whereQuery;
                isWhere=true;
            }

            if(this.groupQuery!=null && !this.groupQuery.isEmpty()){
                groupQ = this.groupQuery;
                isGroup=true;
            }

            if(this.havingQuery!=null && !this.havingQuery.isEmpty()){
                havingQ = this.havingQuery;
                isHaving=true;
            }

            if(this.sortQuery!=null && !this.sortQuery.isEmpty()){
                sortQ = this.sortQuery;
                isSort=true;
            }

            if(this.limitQuery!=null && !this.limitQuery.isEmpty()){
                limitQ = this.limitQuery;
                isLimit=true;
            }
      
    
        if(isWhere){
            whereQ ="\n" + whereQ;
        }
        if(isGroup){
            groupQ ="\n" + groupQ;
        }
        if(isHaving){
            havingQ ="\n" + havingQ;
        }
        if(isSort){
            sortQ ="\n" + sortQ;
        }
        if(isLimit){
            limitQ ="\n" + limitQ;
        }
        statement = statement.replace("{selectQuery}",selectQ);
        statement = statement.replace("{fromQuery}",fromQ);
        statement = statement.replace("{whereQuery}",whereQ);
        statement = statement.replace("{groupQuery}",groupQ);
        statement = statement.replace("{havingQuery}",havingQ);
        statement = statement.replace("{sortQuery}",sortQ);
        statement = statement.replace("{limitQuery}",limitQ);

        String statUnions="";
        if(this.unionList!=null){
            int index=0;
            for (IDBQueryBuilderSelect queryBuilder : unionList) {
                String kkey = unionListKeys.get(index);
                statUnions += "\n"+kkey+"\n" + queryBuilder.buildSelectQuery();
                index++;
            }
        }

        statement += statUnions;
        return statement;
    }
  
    @Override
    public IDBQueryBuilderSelect count(String alias) {
        String statement  = "SELECT COUNT(*) AS {ALIAS}";

        statement = statement.replace("{ALIAS}", alias);
        this.selectQuery = statement;

        return this;
    }

    @Override
    public IDBQueryBuilderSelect count(String column,String alias) {
        String statement  = "SELECT COUNT({COLUMN}) AS {ALIAS}";

        statement = statement.replace("{COLUMN}",column).replace("{ALIAS}",alias);
        this.selectQuery = statement;
        
        return this;
    }
    @Override
    public IDBQueryBuilderSelect avg(String column,String alias) {
        String statement  = "SELECT AVG({COLUMN}) AS {ALIAS}";

        statement = statement.replace("{COLUMN}",column).replace("{ALIAS}",alias);
        this.selectQuery = statement;
        
        return this;
    }
    @Override
    public IDBQueryBuilderSelect max(String column,String alias) {
        String statement  = "SELECT MAX({COLUMN}) AS {ALIAS}";

        statement = statement.replace("{COLUMN}",column).replace("{ALIAS}",alias);
        this.selectQuery = statement;
        
        return this;
    }
    @Override
    public IDBQueryBuilderSelect min(String column,String alias) {
        String statement  = "SELECT mIN({COLUMN}) AS {ALIAS}";

        statement = statement.replace("{COLUMN}",column).replace("{ALIAS}",alias);
        this.selectQuery = statement;
        
        return this;
    }
    @Override
    public IDBQueryBuilderSelect sum(String column,String alias) {
        String statement  = "SELECT SUM({COLUMN}) AS {ALIAS}";

        statement = statement.replace("{COLUMN}",column).replace("{ALIAS}",alias);
        this.selectQuery = statement;
        
        return this;
    }

    @Override
    public IDBQueryBuilderSelect innerJoin(String table,String PKColumn,String FKColumn ) {
        String statement  = "INNER JOIN {TABLE} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", table).replace("{PK}",PKColumn).replace("{FK}",FKColumn);

        this.fromQuery += statement;
        return this;
    }

    @Override
    public IDBQueryBuilderSelect crossJoin(String table) {
        String statement  = "CROSS JOIN {TABLE}) ";
        statement = statement.replace("{TABLE}", table);

        this.fromQuery += statement;
        return this;
    }

    @Override
    public IDBQueryBuilderSelect leftJoin(String table, String PKColumn, String FKColumn) {
        String statement  = "LEFT OUTER JOIN {TABLE} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", table).replace("{PK}",PKColumn).replace("{FK}",FKColumn);

        this.fromQuery += statement;
        return this;
    }

    @Override
    public IDBQueryBuilderSelect rightJoin(String table, String PKColumn, String FKColumn) {
        String statement  = "RIGHT OUTER JOIN {TABLE} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", table).replace("{PK}",PKColumn).replace("{FK}",FKColumn);

        this.fromQuery += statement;
        return this;
    }

    @Override
    public IDBQueryBuilderSelect outerJoin(String table, String PKColumn, String FKColumn) {
        String statement  = "FULL OUTER JOIN {TABLE} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", table).replace("{PK}",PKColumn).replace("{FK}",FKColumn);

        this.fromQuery += statement;
        return this;
    }


    @Override
    public IDBQueryBuilderSelect innerJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn, String FKColumn) {
        String subQueryStatement = subQuery.buildSelectQuery();
        String statement  = "INNER JOIN ({TABLE}) AS {ALIAS} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", subQueryStatement).replace("{ALIAS}",alias).replace("{FK}",FKColumn).replace("{PK}",PKColumn);

        this.fromQuery += statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect leftJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn, String FKColumn) {
        String subQueryStatement = subQuery.buildSelectQuery();
        String statement  = "LEFT OUTER JOIN ({TABLE}) AS {ALIAS} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", subQueryStatement).replace("{ALIAS}",alias).replace("{FK}",FKColumn).replace("{PK}",PKColumn);

        this.fromQuery += statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect rightJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn, String FKColumn) {
        String subQueryStatement = subQuery.buildSelectQuery();
        String statement  = "RIGHT OUTER JOIN ({TABLE}) AS {ALIAS} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", subQueryStatement).replace("{ALIAS}",alias).replace("{FK}",FKColumn).replace("{PK}",PKColumn);

        this.fromQuery += statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect outerJoin(IDBQueryBuilderSelect subQuery,String alias,String PKColumn, String FKColumn) {
        String subQueryStatement = subQuery.buildSelectQuery();
        String statement  = "FULL OUTER JOIN ({TABLE}) AS {ALIAS} on ({PK}={FK}) ";
        statement = statement.replace("{TABLE}", subQueryStatement).replace("{ALIAS}",alias).replace("{FK}",FKColumn).replace("{PK}",PKColumn);

        this.fromQuery += statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect crossJoin(IDBQueryBuilderSelect subQuery) {
        String subQueryStatement = subQuery.buildSelectQuery();
        String statement  = "CROSS JOIN ({TABLE}) ";
        statement = statement.replace("{TABLE}", subQueryStatement);

        this.fromQuery += statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect selectTop(int number,String... columns) {
        if(columns==null || columns.length==0)
        return this;

        String statement = "SELECT TOP {NUM} {COLUMNS} ";
        String cols = "";
        for (String col : columns) {
            cols += col+",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);

        statement = statement.replace("{NUM}",String.valueOf(number)).replace("{COLUMNS}",cols);
        this.selectQuery = statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect selectTop(int number) {
        String statement  = "SELECT TOP {NUM} * ";

        this.selectQuery = statement.replace("{NUM}",String.valueOf(number));
        return this;
    }

    @Override
    public IDBQueryBuilderSelect selectDistinct() {
        String statement = "SELECT DISTINCT * ";
        this.selectQuery = statement;
        return this;
    }

    @Override
    public IDBQueryBuilderSelect selectDistinct(String... columns) {
        if(columns==null || columns.length==0)
        return this;

        String statement = "SELECT DISTINCT {COLUMNS} ";
        String cols = "";
        for (String col : columns) {
            cols += col+",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);

        statement = statement.replace("{COLUMNS}", cols);
        this.selectQuery = statement;
        return this;
    }

    @Override
    public IDBQueryBuilderUpdate update(String[] columns, Object[] data) {
        String statement  = "";
        String cols = "";
        for (int index=0;index<columns.length;index++) {
            String col = columns[index];
            String val=(String) buildConditionValue(DBQueryConditionOpEnum.EQUAL,data[index]);
            cols += col + "=" + val + ",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);

        statement = cols;

        this.updateQuery = statement;
        return this;
    }

    @Override
    public IDBQueryBuilderInsert insert(String[] columns, Object[] data) {
        String statement  = "";
        String cols = "";
        String vals = "";

        for (int index=0;index<columns.length;index++) {
            String col = columns[index];
            cols += col + ",";
        }
        if(columns.length>0)
            cols = cols.substring(0, cols.length()-1);


        for (int index=0;index<data.length;index++) {
            String val=(String) buildConditionValue(DBQueryConditionOpEnum.EQUAL,data[index]);
            vals +=val + ",";
        }
        if(data.length>0)
            vals = vals.substring(0, vals.length()-1);

        statement = "(" + cols + ") VALUES (" + vals + ")";
        this.insertQuery = statement;
        return this;
    }


    @Override
    public String buildDeleteQuery() {
        String statement = "DELETE FROM {TABLE} {whereQuery}";

        statement = statement.replace("{TABLE}",this.tableQuery);

        if(this.whereQuery!=null && !this.whereQuery.isEmpty())
        statement = statement.replace("{whereQuery}","\n"+this.whereQuery); 
        else
        statement = statement.replace("{whereQuery}",""); 
   
        return statement;
    }
    @Override
    public String buildInsertQuery() {
        String statement = "INSERT INTO {TABLE} {insertQuery}";

        statement = statement.replace("{TABLE}",this.tableQuery).replace("{insertQuery}",insertQuery);
        
        return statement;
    }
    @Override
    public String buildUpdateQuery() {
        String statement = "UPDATE {TABLE} set {updateQuery} {whereQuery}";

        statement = statement.replace("{TABLE}",this.tableQuery);
        statement = statement.replace("{updateQuery}",this.updateQuery);

        if( this.whereQuery!=null && !this.whereQuery.isEmpty())
        statement = statement.replace("{whereQuery}","\n"+this.whereQuery); 
        else
        statement = statement.replace("{whereQuery}",""); 
        return statement;
    }
    @Override
    public IDBQueryBuilder get() {
        return this;
    }
    @Override
    public IDBQueryBuilder reset() {
        this.insertQuery="";
        this.deleteQuery="";
        this.updateQuery="";
        this.tableQuery="";
        this.selectQuery="";
        this.whereQuery="";
        this.sortQuery="";
        this.havingQuery="";
        this.groupQuery="";
        this.fromQuery="";
        this.limitQuery="";
        this.unionList=(List<IDBQueryBuilderSelect>) new ArrayList<IDBQueryBuilderSelect>();
        this.unionListKeys=new ArrayList<>();
        return this;
    }

    @Override
    public IDBQueryBuilderSelect limit(int number) {
        String statement = "LIMIT {COUNT}";

        statement = statement.replace("{COUNT}",String.valueOf(number));
        this.limitQuery = statement;
        return this;
    }
    @Override
    public IDBQueryBuilderSelect limit(int number,int skip) {
        String statement = "LIMIT {COUNT} OFFSET {SKIP}";

        statement = statement.replace("{COUNT}",String.valueOf(number));
        statement = statement.replace("{SKIP}",String.valueOf(skip));
        this.limitQuery = statement;
        return this;
    }

    @Override
    public IDBQueryBuilder clone(){
        return new DBSqlQueryBuilder(this);
    }
    @Override
    public IDBQueryBuilderSelect union(IDBQueryBuilderSelect queryBuilder) {
        this.unionList.add(queryBuilder);
        this.unionListKeys.add("UNION");
        return this;
    }
    @Override
    public IDBQueryBuilderSelect unionAll(IDBQueryBuilderSelect queryBuilder) {
        this.unionList.add(queryBuilder);
        this.unionListKeys.add("UNION ALL");
        return this;
    }
   

    private String TableStatement(String name){
        String statement = " {TABLE} ";
        statement = statement.replace("{TABLE}", name);

        this.tableQuery = statement;
        return  this.tableQuery ;
    }
    @Override
    public IDBQueryBuilderDelete deleteTable(String name) {
        if(name == null || name == "")
        return (IDBQueryBuilder)this;

        this.tableQuery = TableStatement(name);
        return (IDBQueryBuilder)this;
    }

    @Override
    public IDBQueryBuilderInsert insertTable(String name) {
        if(name == null || name == "")
        return (IDBQueryBuilder)this;

        this.tableQuery = TableStatement(name);

        return (IDBQueryBuilder)this;
    }
    @Override
    public IDBQueryBuilderUpdate updateTable(String name) {
        if(name == null || name == "")
        return (IDBQueryBuilder)this;

        this.tableQuery = TableStatement(name);

        return (IDBQueryBuilder)this;
    }
    @Override
    public IDBQueryBuilderSelect from(IDBQueryBuilderSelect subQuery, String alias) {
        if(subQuery == null)
        return this;

        String subQueryStatement = subQuery.buildSelectQuery();
        if(subQueryStatement==null || subQueryStatement.isEmpty())
        return this;

        String statement = "FROM ({TABLE}) AS {ALIAS} ";
        statement = statement.replace("{TABLE}", subQueryStatement).replace("{ALIAS}", alias);

        this.fromQuery = statement;
        return this;
    }
   
}

package com.core.shared.Abstructions.DBManager;

import java.sql.ResultSet;

public interface IDBManager {
    public ResultSet select(String statement);
    public int insert(String statement);
    public int update(String statement);
    public int delete(String statement);

}

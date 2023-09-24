package com.core.shared.Classes.DBManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.core.shared.Abstructions.DBManager.IDBManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;
public class DBPostresManager implements IDBManager {

    private DBManagerConnection conn;
    private String className="org.postgresql.Driver";
    private boolean withTransaction;
    private ILoggerManager logger;
    public DBPostresManager(ILoggerManager logger, DBManagerConnection conn,boolean withTransaction){
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        this.conn=conn;
        this.withTransaction=withTransaction;
        this.logger = logger;
    }
    private Connection getConnection (DBManagerConnection conn){
        try{
            Connection connection=DriverManager.getConnection(conn.getUrl(),conn.getUserName(),conn.getPassword());  
            return connection;
        }catch(Exception e){
            logger.log(new LoggerMessage(null,e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
            return null;
        }
    }
    @Override
    public ResultSet select(String statement) {
        try{      
            Connection connection=DriverManager.getConnection(this.conn.getUrl(),this.conn.getUserName(),this.conn.getPassword());  
            Statement cmd=connection.createStatement();  
            ResultSet result=cmd.executeQuery(statement);   
            connection.close();  
            return result;
        }catch(Exception e){
            logger.log(new LoggerMessage(null,e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
            return null;
        }  
    }
    @Override
    public int insert(String statement) {
        Connection connection = getConnection(conn);
        if(connection==null)
        return -1;

        try{      
            connection.setAutoCommit(!this.withTransaction);
            Statement cmd=connection.createStatement();  
            int result=cmd.executeUpdate(statement);    
            if(this.withTransaction)
                connection.commit();       
            connection.close();  
            return result;
        }catch(Exception e){
            if(this.withTransaction)
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.log(new LoggerMessage(null,e1.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
                }
                logger.log(new LoggerMessage(null,e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
            return -1;
        }  
    }
    @Override
    public int update(String statement) {
        Connection connection = getConnection(conn);
        if(connection==null)
        return -1;

        try{      
            connection.setAutoCommit(!this.withTransaction);
            Statement cmd=connection.createStatement();  
            int result=cmd.executeUpdate(statement);    
            if(this.withTransaction)
                connection.commit();       
            connection.close();  
            return result;
        }catch(Exception e){
            if(this.withTransaction)
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.log(new LoggerMessage(null,e1.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
                }
                logger.log(new LoggerMessage(null,e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
            return -1;
        }  
    }
    @Override
    public int delete(String statement) {
        Connection connection = getConnection(conn);
        if(connection==null)
        return -1;

        try{      
            connection.setAutoCommit(!this.withTransaction);
            Statement cmd=connection.createStatement();  
            int result=cmd.executeUpdate(statement);    
            if(this.withTransaction)
                connection.commit();       
            connection.close();  
            return result;
        }catch(Exception e){
            if(this.withTransaction)
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.log(new LoggerMessage(null,e1.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
                }
                logger.log(new LoggerMessage(null,e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
            return -1;
        }  
    }
}

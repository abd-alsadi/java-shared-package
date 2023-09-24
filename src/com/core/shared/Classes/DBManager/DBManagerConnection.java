package com.core.shared.Classes.DBManager;

public class DBManagerConnection {
    private String url;
    private String userName;
    private String password;
    public DBManagerConnection(String url,String userName,String password){
        this.url=url;
        this.userName=userName;
        this.password=password;
    }
    public String getUrl(){
        return this.url;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
}

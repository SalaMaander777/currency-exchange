package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private final static String DB_URL = "jdbc:sqlite:database.db";
    public static Connection connect(){
        try{
           return DriverManager.getConnection(DB_URL);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            
        }
        return null;
    }
    
}

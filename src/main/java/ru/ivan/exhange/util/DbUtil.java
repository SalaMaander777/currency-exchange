package ru.ivan.exhange.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String  DB_URL = "jdbc:sqlite:C:\\Users\\medno\\Desktop\\exhange\\database.db";

    public static Connection connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.ivan.exhange.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String  DB_URL = "jdbc:sqlite:database.db";

    public static Connection connect(){
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

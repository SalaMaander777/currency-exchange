package ru.ivan.exhange.exceptions;

import java.sql.SQLException;

public class DatabaseConnectionException extends CurrencyException{
    private String message = "База данных не доступна";

    public String getMessage(){
        return message;
    }
}

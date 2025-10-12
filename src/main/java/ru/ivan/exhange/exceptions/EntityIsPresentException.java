package ru.ivan.exhange.exceptions;

public class EntityIsPresentException extends CurrencyException{


    @Override
    public String getMessage() {
        return "Currency already exists";
    }
}

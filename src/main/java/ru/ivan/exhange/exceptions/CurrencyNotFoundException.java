package ru.ivan.exhange.exceptions;

public class CurrencyNotFoundException extends  CurrencyException{
    @Override
    public String getMessage() {
        return "Валюта с таким кодом не найдена";
    }


}

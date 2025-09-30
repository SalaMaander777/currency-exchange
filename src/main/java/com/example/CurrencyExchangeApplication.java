package com.example;

import com.example.util.DbUtil;

public class CurrencyExchangeApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DbUtil.connect();
    }
}
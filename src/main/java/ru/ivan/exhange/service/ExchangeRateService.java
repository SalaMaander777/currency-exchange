package ru.ivan.exhange.service;

import ru.ivan.exhange.repository.echangeRate.ExchangeRateImpl;
import ru.ivan.exhange.repository.echangeRate.ExchangeRateRepository;

public class ExchangeRateService {

    private ExchangeRateRepository exchangeRateRepository;

    public  ExchangeRateService() {
        this.exchangeRateRepository = new ExchangeRateImpl();
    }
}

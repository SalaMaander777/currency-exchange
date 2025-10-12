package ru.ivan.exhange.service;


import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.repository.currency.CurrencyRepository;
import ru.ivan.exhange.repository.currency.CurrencyRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CurrencyService {

    private CurrencyRepository currencyRepository;
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = new CurrencyRepositoryImpl();
    }


    public List<CurrencyEntity> findAll() {
        return currencyRepository.findAll();
    }

    public Optional<CurrencyEntity> findById(Long id) {
        return currencyRepository.findById(id);
    }

    public CurrencyEntity findByCode(String code) {
        return currencyRepository.getByCode(code);
    }







}

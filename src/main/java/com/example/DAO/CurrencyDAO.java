package com.example.DAO;

import java.lang.classfile.ClassFile.Option;
import java.util.List;

import com.example.repositories.CurrencyRepository;
import com.example.models.*;;;

public class CurrencyDAO {

    private CurrencyRepository currencyRepo;
     public CurrencyDAO(CurrencyRepository currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    public List<Currency> getAllCurrencies() {
        
        if (currencyRepo.getCurrencies().isPresent()) {
            return currencyRepo.getCurrencies().get();
        }
        return null;
    }
}

package com.example.services;


import java.util.ArrayList;
import java.util.List;

import com.example.DAO.CurrencyDAO;
import com.example.DTO.CurrencyDTO;
import com.example.models.Currency;

public class CurrencyService {
    private CurrencyDAO currencyDAO;

    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }


    public List<CurrencyDTO> getAllCurrencies(){
        List<Currency> currencies = currencyDAO.getAllCurrencies();
        List<CurrencyDTO> currencyDTOs = new ArrayList<>();
        for(Currency currency : currencies){
            CurrencyDTO currencyDTO = new CurrencyDTO(currency.getId(), currency.getCode(), currency.getFullName(), currency.getSign());
            currencyDTOs.add(currencyDTO);
        }
        return currencyDTOs;
    }
}

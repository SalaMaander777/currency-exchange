package ru.ivan.exhange.service;

import ru.ivan.exhange.dto.ExchangeRateDto;
import ru.ivan.exhange.dto.ExchangeRateResponse;
import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.entities.ExchangeRateEntity;
import ru.ivan.exhange.exceptions.DatabaseConnectionException;
import ru.ivan.exhange.exceptions.EntityIsPresentException;
import ru.ivan.exhange.mapper.CurrencyMapper;
import ru.ivan.exhange.mapper.ExchangeRateMapper;
import ru.ivan.exhange.repository.currency.CurrencyRepository;
import ru.ivan.exhange.repository.currency.CurrencyRepositoryImpl;
import ru.ivan.exhange.repository.echangeRate.ExchangeRateImpl;
import ru.ivan.exhange.repository.echangeRate.ExchangeRateRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ExchangeRateService {

    private ExchangeRateRepository exchangeRateRepository;
    private CurrencyRepository currencyRepository;
    private ExchangeRateMapper mapper;
    private CurrencyMapper currencyMapper;


    public  ExchangeRateService() {
        this.exchangeRateRepository = new ExchangeRateImpl();
        this.mapper = new ExchangeRateMapper();
        this.currencyMapper = new CurrencyMapper();
        this.currencyRepository = new CurrencyRepositoryImpl();
    }


    public List<ExchangeRateDto> getAll(){
        List< ExchangeRateEntity> exchangeRates  = exchangeRateRepository.findAll();
        if(exchangeRates == null ) {
            throw new DatabaseConnectionException();
        }
        return mapper.toListDto(exchangeRates) ;
    }

    public ExchangeRateResponse getByPair(String code1, String code2) {
        Optional<CurrencyEntity> currency1 = currencyRepository.getByCode(code1);
        Optional<CurrencyEntity> currency2 = currencyRepository.getByCode(code2);
        Optional<ExchangeRateEntity> exchangeRate = exchangeRateRepository.getByPair(currency1.get().getId(), currency2.get().getId());
        if(exchangeRate.isEmpty()) {
            throw new EntityIsPresentException();
        }
        return new ExchangeRateResponse(exchangeRate.get().getId(),
                currencyMapper.toCurrencyDto(currency1.get()), currencyMapper.toCurrencyDto(currency2.get()), exchangeRate.get().getRate());


    }

    public ExchangeRateResponse save(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate) {
        Optional<CurrencyEntity> baseCurrency = currencyRepository.getByCode(baseCurrencyCode);
        Optional<CurrencyEntity> targetCurrency = currencyRepository.getByCode(targetCurrencyCode);
        ExchangeRateEntity exchangeRate = new ExchangeRateEntity(baseCurrency.get().getId(), targetCurrency.get().getId(), rate);


            ExchangeRateEntity exchangeRateEntity = exchangeRateRepository.save(exchangeRate);

            return new ExchangeRateResponse(
                    exchangeRateEntity.getId(),
                    currencyMapper.toCurrencyDto(baseCurrency.orElse(null)),  currencyMapper.toCurrencyDto(targetCurrency.orElse(null)),
                    exchangeRate.getRate()
            );







    }




}

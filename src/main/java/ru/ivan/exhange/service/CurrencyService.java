package ru.ivan.exhange.service;


import ru.ivan.exhange.dto.CurrencyDto;
import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.exceptions.CurrencyNotFoundException;
import ru.ivan.exhange.exceptions.DatabaseConnectionException;
import ru.ivan.exhange.exceptions.EntityIsPresentException;
import ru.ivan.exhange.mapper.CurrencyMapper;
import ru.ivan.exhange.repository.currency.CurrencyRepository;
import ru.ivan.exhange.repository.currency.CurrencyRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper mapper;
    public CurrencyService() {
        this.currencyRepository = new CurrencyRepositoryImpl();
        this.mapper = new CurrencyMapper();
    }


    public List<CurrencyDto> findAll() throws DatabaseConnectionException {
            List<CurrencyEntity> currencyEntities = currencyRepository.findAll();
            if(currencyEntities == null ) {
                throw new DatabaseConnectionException();
            }
            return mapper.toCurrencyDtoList(currencyEntities);

    }

    public Optional<CurrencyEntity> findById(Long id){
        return currencyRepository.findById(id);
    }

    public CurrencyDto findByCode(String code) {
        Optional<CurrencyEntity> currency = currencyRepository.getByCode(code);
        if (currency.isEmpty()) throw new CurrencyNotFoundException();
        return mapper.toCurrencyDto(currency.orElse(null));
    }

    public CurrencyDto save(CurrencyDto currencyDto) {
        CurrencyEntity currencyEntity = mapper.toCurrencyEntity(currencyDto);

        CurrencyEntity currencyReturned = currencyRepository.save(currencyEntity);
        return mapper.toCurrencyDto(currencyReturned);
    }






}

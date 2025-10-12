package ru.ivan.exhange.service;


import ru.ivan.exhange.dto.CurrencyDto;
import ru.ivan.exhange.entities.CurrencyEntity;
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


    public List<CurrencyDto> findAll() {
        return mapper.toCurrencyDtoList(currencyRepository.findAll());
    }

    public Optional<CurrencyEntity> findById(Long id) {
        return currencyRepository.findById(id);
    }

    public CurrencyDto findByCode(String code) {
        return mapper.toCurrencyDto(currencyRepository.getByCode(code));
    }

    public CurrencyDto save(CurrencyDto currencyDto) {
        CurrencyEntity currencyEntity = mapper.toCurrencyEntity(currencyDto);
        CurrencyEntity currencyReturned = currencyRepository.save(currencyEntity);
        return mapper.toCurrencyDto(currencyReturned);
    }







}

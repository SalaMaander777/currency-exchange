package ru.ivan.exhange.mapper;

import ru.ivan.exhange.dto.CurrencyDto;
import ru.ivan.exhange.entities.CurrencyEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class CurrencyMapper {

    public CurrencyDto toCurrencyDto(CurrencyEntity currencyEntity){
        return new CurrencyDto(
                currencyEntity.getId(),
                currencyEntity.getName(),
                currencyEntity.getCode(),
                currencyEntity.getSign()
        );
    }

    public CurrencyEntity toCurrencyEntity(CurrencyDto currencyDto){
        return new CurrencyEntity(
                currencyDto.getName(),
                currencyDto.getCode(),
                currencyDto.getSign()
        );
    }

    public List<CurrencyDto> toCurrencyDtoList(List<CurrencyEntity> currencyEntities){
        List<CurrencyDto> currencyDtoList = new ArrayList<>();
        for(CurrencyEntity currencyEntity : currencyEntities){
           currencyDtoList.add(toCurrencyDto(currencyEntity)) ;
        }
        return currencyDtoList;
    }

    public List<CurrencyEntity> toCurrencyEntityList(List<CurrencyDto> currencyDtoList){
        List<CurrencyEntity> currencyEntityList = new ArrayList<>();
        for(CurrencyDto currencyDto : currencyDtoList){
            currencyEntityList.add(toCurrencyEntity(currencyDto)) ;
        }
        return currencyEntityList;
    }
}

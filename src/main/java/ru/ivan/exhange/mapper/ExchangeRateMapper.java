package ru.ivan.exhange.mapper;

import ru.ivan.exhange.dto.ExchangeRateDto;
import ru.ivan.exhange.entities.ExchangeRateEntity;
import ru.ivan.exhange.service.ExchangeRateService;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateMapper {

    public ExchangeRateDto toExchangeRateDto(ExchangeRateEntity entity){
        return new ExchangeRateDto(
            entity.getId(),
            entity.getBaseCurrencyId(),
            entity.getTargetCurrencyId(),
            entity.getRate()
        );
    }


    public ExchangeRateEntity toExchangeRateEntity(ExchangeRateDto dto){
        return new ExchangeRateEntity(
                dto.getBaseCurrencyId(),
                dto.getTargetCurrencyId(),
                dto.getRate()
        );
    }

    public List<ExchangeRateEntity> toListEntity(List<ExchangeRateDto> dtos){
        List<ExchangeRateEntity> entities = new ArrayList<>();
        for(ExchangeRateDto dto: dtos) {
            entities.add(toExchangeRateEntity(dto));
        }
        return entities;
    }

    public List<ExchangeRateDto> toListDto(List<ExchangeRateEntity> entities){
        List<ExchangeRateDto> dtos = new ArrayList<>();
        for (ExchangeRateEntity entity: entities){
            dtos.add(toExchangeRateDto(entity));
        }
        return dtos;
    }

}

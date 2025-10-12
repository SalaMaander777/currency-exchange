package ru.ivan.exhange.repository.currency;

import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.repository.orm.CrudRepository;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity,Long> {

    CurrencyEntity getByCode(String code);
}

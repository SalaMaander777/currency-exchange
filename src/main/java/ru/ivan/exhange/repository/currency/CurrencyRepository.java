package ru.ivan.exhange.repository.currency;

import ru.ivan.exhange.entities.CurrencyEntity;
import ru.ivan.exhange.repository.orm.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity,Long> {

    Optional<CurrencyEntity> getByCode(String code);
}

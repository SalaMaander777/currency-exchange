package ru.ivan.exhange.repository.echangeRate;

import ru.ivan.exhange.entities.ExchangeRateEntity;
import ru.ivan.exhange.repository.orm.AbstractJDBCRepository;
import ru.ivan.exhange.repository.orm.CrudRepository;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRateEntity,Long> {
}

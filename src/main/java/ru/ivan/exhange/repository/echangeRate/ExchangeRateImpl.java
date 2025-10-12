package ru.ivan.exhange.repository.echangeRate;

import ru.ivan.exhange.entities.ExchangeRateEntity;
import ru.ivan.exhange.repository.orm.AbstractJDBCRepository;
import ru.ivan.exhange.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeRateImpl  extends AbstractJDBCRepository<ExchangeRateEntity> implements ExchangeRateRepository {
    @Override
    protected String getTableName() {
        return "ExchangeRates";
    }

    @Override
    protected ExchangeRateEntity mapResultSet(ResultSet rs) throws SQLException {
        return new ExchangeRateEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getBigDecimal(4)
        );
    }

    @Override
    public ExchangeRateEntity save(ExchangeRateEntity entity) {
        String SQLQuery = """
                INSERT INTO ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate) VALUES (?, ?, ?)""";
        try(Connection connection = DbUtil.connect(); PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
            statement.setLong(1, entity.getBaseCurrencyId());
            statement.setLong(2, entity.getTargetCurrencyId());
            statement.setBigDecimal(3, entity.getRate());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return new ExchangeRateEntity(
                        rs.getLong(1),
                        entity.getBaseCurrencyId(),
                        entity.getTargetCurrencyId(),
                        entity.getRate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ExchangeRateEntity update(ExchangeRateEntity entity) {
        return null;
    }
}

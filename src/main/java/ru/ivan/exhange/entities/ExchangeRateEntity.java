package ru.ivan.exhange.entities;

import java.math.BigDecimal;


public class ExchangeRateEntity {
    private Long id;
    private Long BaseCurrencyId;
    private Long TargetCurrencyId;

    private BigDecimal rate;

    public ExchangeRateEntity(Long id, Long baseCurrencyId, Long targetCurrencyId, BigDecimal rate) {
        this.id = id;
        BaseCurrencyId = baseCurrencyId;
        TargetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public ExchangeRateEntity(Long baseCurrencyId, Long targetCurrencyId, BigDecimal rate) {
        BaseCurrencyId = baseCurrencyId;
        TargetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public Long getTargetCurrencyId() {
        return TargetCurrencyId;
    }

    public void setTargetCurrencyId(Long targetCurrencyId) {
        TargetCurrencyId = targetCurrencyId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getBaseCurrencyId() {
        return BaseCurrencyId;
    }

    public void setBaseCurrencyId(Long baseCurrencyId) {
        BaseCurrencyId = baseCurrencyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

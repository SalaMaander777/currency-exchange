package ru.ivan.exhange.dto;

import java.math.BigDecimal;

public class ExchangeRateDto {
    private Long id;
    private Long BaseCurrencyId;
    private Long TargetCurrencyId;

    private BigDecimal rate;

    public ExchangeRateDto(Long id, Long baseCurrencyId, Long targetCurrencyId, BigDecimal rate) {
        this.id = id;
        BaseCurrencyId = baseCurrencyId;
        TargetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public ExchangeRateDto(Long baseCurrencyId, Long targetCurrencyId, BigDecimal rate) {
        BaseCurrencyId = baseCurrencyId;
        TargetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBaseCurrencyId() {
        return BaseCurrencyId;
    }

    public void setBaseCurrencyId(Long baseCurrencyId) {
        BaseCurrencyId = baseCurrencyId;
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
}

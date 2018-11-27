package com.clemencio.morales.model;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {

    private static final Currency EUR = Currency.getInstance("EUR");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private final BigDecimal amount;
    private final Currency currency;

    private Money(final BigDecimal amount, final Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    private Money(final BigDecimal amount, final Currency currency, final RoundingMode rounding) {
        this.currency = currency;
        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
    }

    public static Money euros(final BigDecimal amount) {
        if (!isGreaterThanZeroAmount(amount)) {
            throw new IllegalArgumentException("Money cannot be negative. Amount: " + amount);
        }
        return new Money(amount, EUR);
    }

    public static boolean isGreaterThanZeroAmount(final BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 1;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return getCurrency().getSymbol() + StringUtils.SPACE + getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!amount.equals(money.amount)) return false;
        return currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}

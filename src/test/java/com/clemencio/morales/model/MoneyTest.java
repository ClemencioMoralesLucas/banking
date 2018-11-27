package com.clemencio.morales.model;

import com.clemencio.morales.bean.test.BeanTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class MoneyTest extends BeanTest<Money> {

    private Money moneyAverage = Money.euros(new BigDecimal(1_000));
    private Money moneyHigh = Money.euros(new BigDecimal(8_000));
    private Money moneyLow = Money.euros(BigDecimal.ONE);

    @Override
    protected Money getBean() {
        return Money.euros(BigDecimal.ONE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = ".*Money cannot be negative. Amount: -1")
    public void testEurosThrowsIllegalArgumentExceptionIfNegativeAmountGiven() {
        Money.euros(new BigDecimal(-1));
    }

    @Test
    public void testHashCodeIsConsistent() {
        assertEquals(moneyAverage.hashCode(), Money.euros(new BigDecimal(1000)).hashCode());
        assertNotEquals(moneyAverage.hashCode(), Money.euros(BigDecimal.ZERO).hashCode());
    }

    @Test
    public void testEqualsIsConsistent() {
        assertEquals(moneyAverage, moneyAverage);
        assertNotEquals(moneyHigh, moneyAverage);
        assertNotEquals(moneyAverage, moneyHigh);
        assertNotEquals(null, moneyHigh);
        assertNotEquals(StringUtils.EMPTY, moneyHigh);
        assertNotEquals(moneyLow, moneyHigh);
        assertNotEquals(moneyLow, null);
    }
}
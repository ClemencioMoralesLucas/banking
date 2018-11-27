package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class BankAccountTest {

    private BankAccount savingAccount;

    @BeforeMethod
    public void setUp() {
        this.savingAccount = new SavingAccount("John Wick", Money.euros(new BigDecimal(10_000)), new BigDecimal(5.4));
    }

    @Test
    public void testDepositMoneyUpdatesBalanceIfValidAmountGiven() {
        final Money expectedBalance = Money.euros(new BigDecimal(10_010));
        this.savingAccount.depositMoney(Money.euros(BigDecimal.TEN));
        assertEquals(this.savingAccount.getBalance(), expectedBalance);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Money cannot be negative. Amount: -1")
    public void testDepositMoneyThrowsIllegalArgumentExceptionIfNegativeAmountGiven() {
        this.savingAccount.depositMoney(Money.euros(new BigDecimal(-1)));
    }

    @Test
    public void testWithdrawMoneyUpdatesBalanceIfValidAmount() {
        Money expectedBalance = Money.euros(new BigDecimal(9999.28));
        Money amountToWithdraw = Money.euros(new BigDecimal(0.72));
        this.savingAccount.withdrawMoney(amountToWithdraw);
        assertEquals(this.savingAccount.getBalance(), expectedBalance);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Money cannot be negative. Amount: -1")
    public void testWithdrawMoneyThrowsIllegalArgumentExceptionIfNegativeAmountGiven() {
        this.savingAccount.withdrawMoney(Money.euros(new BigDecimal(-1)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = ".*security threshold cannot be broken.")
    public void testSetBalanceThrowsIllegalArgumentExceptionIfUpperLimitBroken() {
        this.savingAccount.setBalance(Money.euros(BankAccount.MAXIMUM_ALLOWED_AMOUNT));
    }
//
//    @Test
//    public void testSetBalanceThrowsIllegalArgumentExceptionIfLowerLimitBroken() { //-1_000_000????
//
//    }
}
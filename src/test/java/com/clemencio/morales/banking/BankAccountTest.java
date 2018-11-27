package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BankAccountTest {

    private static final String TEST_OWNER = "John Wick";
    private BankAccount savingAccount;

    @BeforeMethod
    public void setUp() {
        this.savingAccount = new SavingAccount(TEST_OWNER, Money.euros(new BigDecimal(10_000)),
                new BigDecimal(5.4));
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

    @Test
    public void testGetOwnerIsConsistent() {
        assertEquals(this.savingAccount.getOwner(), TEST_OWNER);
    }

    @Test
    public void testBankAccountTypeIsConsistent() {
        assertEquals(this.savingAccount.getBankAccountType(), BankAccountType.SAVING);
    }

    @Test
    public void testHasFundsReturnsConsistentValue() {
        assertTrue(this.savingAccount.hasFunds());
        this.savingAccount.setBalance(Money.euros(BigDecimal.ZERO));
        assertFalse(this.savingAccount.hasFunds());
    }

    @Test
    public void testHasEnoughFundsReturnsConsistentValue() {
        assertTrue(this.savingAccount.hasEnoughFunds(Money.euros(new BigDecimal(9999.99))));
        assertTrue(this.savingAccount.hasEnoughFunds(Money.euros(new BigDecimal(10000))));
        assertFalse(this.savingAccount.hasEnoughFunds(Money.euros(new BigDecimal(10000.01))));
        assertFalse(this.savingAccount.hasEnoughFunds(Money.euros(new BigDecimal(10001))));
    }

    @Test
    public void testToStringReflectsNeededInfo() {
        assertEquals(this.savingAccount.toString(),
                "BankAccount{owner='John Wick', balance=EUR 10000.00, bankAccountType=SAVING}");
    }
}
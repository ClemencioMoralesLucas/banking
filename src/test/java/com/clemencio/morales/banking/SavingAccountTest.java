package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class SavingAccountTest {

    private BankAccount savingAccountOrigin;
    private BankAccount savingAccountDestiny;

    @BeforeMethod
    public void setUp() {
        this.savingAccountOrigin = new SavingAccount("Liquid Snake", Money.euros(new BigDecimal(10_000)),
                new BigDecimal(5));
        this.savingAccountDestiny = new SavingAccount("Revolver Ocelot", Money.euros(new BigDecimal(5_000)),
                new BigDecimal(3.5));
    }

    @Test
    public void testCalculateAndPayInterest() {
        this.savingAccountDestiny.calculateAndPayInterest(this.savingAccountOrigin,
                Money.euros(new BigDecimal(1_000)));
        assertEquals(this.savingAccountDestiny.getBalance(), Money.euros(new BigDecimal(6_035)));
        assertEquals(this.savingAccountOrigin.getBalance(), Money.euros(new BigDecimal(8_965)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = ".*Error: Insufficient funds .* to pay EUR 10013.62")
    public void testCalculateAndPayInterestThrowsIllegalArgumentExceptionIfNoEnoughFunds() {
        this.savingAccountDestiny.calculateAndPayInterest(this.savingAccountOrigin,
                Money.euros(new BigDecimal(9_675)));
    }

    @Test(expectedExceptions = UnsupportedOperationException.class,
            expectedExceptionsMessageRegExp = "Cannot transfer from other accounts in Saving Accounts")
    public void testTransferFromCheckingAccountThrowsUnsupportedOperationException() {
        this.savingAccountOrigin.transferFromCheckingAccount(null, null);
    }
}

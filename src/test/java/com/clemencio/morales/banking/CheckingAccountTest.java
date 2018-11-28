package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class CheckingAccountTest {

    public static final Money TEST_MONEY_THOUSAND_EUROS = Money.euros(new BigDecimal(1000));
    private BankAccount checkingAccountOrigin;
    private BankAccount checkingAccountDestiny;

    @BeforeMethod
    public void setUp() {
        this.checkingAccountOrigin = new CheckingAccount("Vulcan Raven", Money.euros(new BigDecimal(5_000)),
                new BigDecimal(1_000));
        this.checkingAccountDestiny = new CheckingAccount("Psycho Mantis", Money.euros(new BigDecimal(3_000)),
                new BigDecimal(500));
    }

    @Test
    public void testTransferFromCheckingAccount() {
        this.checkingAccountDestiny.transferFromCheckingAccount(checkingAccountOrigin,
                Money.euros(new BigDecimal(500)));
        assertEquals(checkingAccountDestiny.getBalance(), Money.euros(new BigDecimal(3_500)));
        assertEquals(checkingAccountOrigin.getBalance(), Money.euros(new BigDecimal(4_500)));
    }

    @Test
    public void testTransferFromCheckingAccountRespectsOverdraftLimit() {
        testTransferFromCheckingAccountWithCustomParameters(Money.euros(new BigDecimal(5550)),
                Money.euros(new BigDecimal(8550)), Money.debtInEuros(new BigDecimal(-550)));
    }

    @Test
    public void testTransferFromCheckingAccountRespectsOverdraftLimitStrictly() {
        testTransferFromCheckingAccountWithCustomParameters(Money.euros(new BigDecimal(6000)),
                Money.euros(new BigDecimal(9000)), Money.debtInEuros(new BigDecimal(-1000)));
    }

    private void testTransferFromCheckingAccountWithCustomParameters(final Money transferAmount,
                                                                     final Money expectedDestinyBalance,
                                                                     final Money expectedOriginBalance) {
        this.checkingAccountDestiny.transferFromCheckingAccount(checkingAccountOrigin, transferAmount);
        assertEquals(checkingAccountDestiny.getBalance(), expectedDestinyBalance);
        assertEquals(checkingAccountOrigin.getBalance(), expectedOriginBalance);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = ".*Error: Checking account overdrawn, cannot transfer " +
                    "amount: EUR -1001.00 due to overdraft limit: -1000")
    public void testTransferFromCheckingAccountThrowsIllegalArgumentExceptionIfOverdrawnPresent() {
        this.checkingAccountDestiny.transferFromCheckingAccount(checkingAccountOrigin,
                Money.euros(new BigDecimal(6001)));
    }

    @Test
    public void testSetOverdraftLimitWithValidInput() {
        assertEquals(this.checkingAccountDestiny.getOverdraftLimit(), new BigDecimal(-500));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Invalid overdraft limit: null")
    public void testSetOverdraftLimitWithNullThrowsIllegalArgumentException() {
        this.checkingAccountDestiny.setOverdraftLimit(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Invalid overdraft limit: -1000")
    public void testSetOverdraftLimitWithNegativeValueThrowsIllegalArgumentException() {
        this.checkingAccountDestiny.setOverdraftLimit(new BigDecimal(-1000));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Invalid overdraft limit: 5001")
    public void testSetOverdraftLimitWithMassiveValueThrowsIllegalArgumentException() {
        this.checkingAccountDestiny.setOverdraftLimit(new BigDecimal(5001));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*Error: Transfer is "
            + "only allowed between Checking Accounts")
    public void testTransferFromSavingAccountIsNotAllowed() {
        final BankAccount savingAccount = new SavingAccount("Sniper Wolf", TEST_MONEY_THOUSAND_EUROS,
                new BigDecimal(5.4));
        checkingAccountOrigin.transferFromCheckingAccount(savingAccount, TEST_MONEY_THOUSAND_EUROS);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class, expectedExceptionsMessageRegExp = ".*Error: Cannot " +
            "calculate and pay interests in Checking Accounts")
    public void testCalculateAndPayInterestIsNotAllowedOnCheckingAccounts() {
        this.checkingAccountOrigin.calculateAndPayInterest(checkingAccountDestiny, TEST_MONEY_THOUSAND_EUROS);
    }
}

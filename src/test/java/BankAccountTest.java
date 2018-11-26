import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.testng.Assert.assertEquals;

public class BankAccountTest {

    private BankAccount savingAccount;
    private BankAccount checkingAccount;

    @BeforeMethod
    public void setUp() {
        this.savingAccount = new SavingAccount("John Wick", new BigDecimal(10_000), new BigDecimal(5.4));
        //this.checkingAccount = new CheckingAccount(new BigDecimal(1000));
    }

    @Test
    public void testDepositUpdatesBalanceIfValidAmountGiven() {
        final BigDecimal expectedBalance = new BigDecimal(10_010);
        this.savingAccount.deposit(BigDecimal.TEN);
        assertEquals(this.savingAccount.getBalance(), expectedBalance);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = ".*Error: Amount to deposit cannot be negative")
    public void testDepositThrowsIllegalArgumentExceptionIfNegativeAmountGiven() {
        this.savingAccount.deposit(new BigDecimal(-1));
    }

    /*@Test                  //TODO KEEP WITH ROUNDING, TESTING BANKACCOUNT.JAVA
    public void testWithdrawalUpdatesBalanceIfValidAmount() {
        final BigDecimal expectedBalance = new BigDecimal(9999.28);
        expectedBalance.setScale(2, RoundingMode.CEILING);
        final BigDecimal amountToWithdraw = new BigDecimal(0.72);
        amountToWithdraw.setScale(2, RoundingMode.CEILING);
        this.savingAccount.withdraw(amountToWithdraw);
        assertEquals(this.savingAccount.getBalance(), expectedBalance);
    }     */

    @Test
    public void testDeposit() {
    }

    @Test
    public void testSetOwner() {
    }

    @Test
    public void testSetBalanceThrowsIllegalArgumentExceptionIfUpperLimitBroken() { //1_000_000

    }

    @Test
    public void testSetBalanceThrowsIllegalArgumentExceptionIfLowerLimitBroken() { //-1_000_000????

    }
}
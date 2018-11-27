import com.sunhill.banking.BankAccount;
import com.sunhill.banking.SavingAccount;
import com.sunhill.model.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class BankAccountTest {

    private BankAccount savingAccount;
    private BankAccount checkingAccount;

    @BeforeMethod
    public void setUp() {
        this.savingAccount = new SavingAccount("John Wick", Money.euros(new BigDecimal(10_000)), new BigDecimal(5.4));
        //this.checkingAccount = new CheckingAccount(new BigDecimal(1000));
    }

    @Test
    public void testDepositUpdatesBalanceIfValidAmountGiven() {
        final Money expectedBalance = Money.euros(new BigDecimal(10_010));
        this.savingAccount.depositMoney(Money.euros(BigDecimal.TEN));
        assertEquals(this.savingAccount.getBalance(), expectedBalance);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Money cannot be negative. Amount: -1")
    public void testDepositThrowsIllegalArgumentExceptionIfNegativeAmountGiven() {
        this.savingAccount.depositMoney(Money.euros(new BigDecimal(-1)));
    }

    @Test
    public void testWithdrawalUpdatesBalanceIfValidAmount() {
        Money expectedBalance = Money.euros(new BigDecimal(9999.28));
        Money amountToWithdraw = Money.euros(new BigDecimal(0.72));
        this.savingAccount.withdrawMoney(amountToWithdraw);
        assertEquals(this.savingAccount.getBalance(), expectedBalance);
    }

//    @Test
//    public void testDeposit() {
//    }
//
//    @Test
//    public void testSetOwner() {
//    }
//
//    @Test
//    public void testSetBalanceThrowsIllegalArgumentExceptionIfUpperLimitBroken() { //1_000_000
//
//    }
//
//    @Test
//    public void testSetBalanceThrowsIllegalArgumentExceptionIfLowerLimitBroken() { //-1_000_000????
//
//    }
}
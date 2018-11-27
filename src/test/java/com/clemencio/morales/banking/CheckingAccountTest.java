package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;

public class CheckingAccountTest {

    private BankAccount checkingAccountOrigin;
    private BankAccount checkingAccountDestiny;

    @BeforeMethod
    public void setUp() {
        this.checkingAccountOrigin = new CheckingAccount("Vulcan Raven", Money.euros(new BigDecimal(5_000)),
                new BigDecimal(1_000));
        this.checkingAccountDestiny = new CheckingAccount("Psycho Mantis", Money.euros(new BigDecimal(3_000)),
                new BigDecimal(500));
    }

//    @Test //todo fixme
//    public void testTransferFromCheckingAccount() {
//        this.checkingAccountDestiny.transferFromCheckingAccount(checkingAccountOrigin,
//                Money.euros(new BigDecimal(500)));
//        assertEquals(checkingAccountDestiny.getBalance(), Money.euros(new BigDecimal(3_500)));
//        assertEquals(checkingAccountOrigin.getBalance(), Money.euros(new BigDecimal(4_500)));
//    }

}
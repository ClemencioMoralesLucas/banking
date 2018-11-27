package com.sunhill.banking;

import com.sunhill.model.Money;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private BigDecimal interestRate;

    public SavingAccount(final String owner, final Money balance, final BigDecimal interestRate) {
        super(owner, balance, BankAccountType.SAVING);
        this.setInterestRate(interestRate);
    }

    private static BigDecimal percentage(final BigDecimal base, final BigDecimal percentage) {
        return base.multiply(percentage).divide(ONE_HUNDRED);
    }

    public void calculateAndPayInterest(final BankAccount bankAccountOther, final Money initialAmount) {
        final Money interest = Money.euros(percentage(initialAmount.getAmount(), interestRate));
        final Money amountWithInterest = Money.euros(interest.getAmount().add(initialAmount.getAmount()));
        if (!bankAccountOther.hasEnoughFunds(amountWithInterest)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Insufficient funds in " + bankAccountOther + " to pay " + amountWithInterest);
        }
        this.setBalance(Money.euros(this.getBalance().getAmount().add(amountWithInterest.getAmount())));
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(final BigDecimal interestRate) { //check is valid interestrate
        this.interestRate = interestRate;
    }
}

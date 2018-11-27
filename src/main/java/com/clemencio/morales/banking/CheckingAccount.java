package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {

    private BigDecimal overdraftLimit;

    public CheckingAccount(final String owner, final Money balance, final BigDecimal overdraftLimit) {
        super(owner, balance, BankAccountType.CHECKING);
        this.setOverdraftLimit(overdraftLimit);
    }

    public void transferFromCheckingAccount(final CheckingAccount otherCheckingAccount, final Money money) {
        if (!otherCheckingAccount.getBankAccountType().equals(BankAccountType.CHECKING)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Transfer is only allowed between Checking Accounts");
        }
        otherCheckingAccount.setBalance(Money.euros(otherCheckingAccount.getBalance().getAmount()
                .subtract(money.getAmount())));
        this.setBalance(Money.euros(this.getBalance().getAmount().add(money.getAmount())));
    }

    @Override
    public void setBalance(final Money money) {
        final Money possibleFinalBalance = Money.euros(this.getBalance().getAmount().subtract(money.getAmount()));
        if (possibleFinalBalance.getAmount().compareTo(overdraftLimit) == -1) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Checking account overdrawn, cannot transfer amount");
        } else {
            super.setBalance(money);
        }
    }

    public BigDecimal getOverdraftLimit() {
        return this.overdraftLimit;
    }

    public void setOverdraftLimit(final BigDecimal overdraftLimit) { //check is valid
        this.overdraftLimit = overdraftLimit;
    }
}

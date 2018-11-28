package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {

    private BigDecimal overdraftLimit;

    public CheckingAccount(final String owner, final Money balance, final BigDecimal overdraftLimit) {
        super(owner, balance, BankAccountType.CHECKING);
        this.setOverdraftLimit(overdraftLimit);
    }

    @Override
    public void transferFromCheckingAccount(final BankAccount otherCheckingAccount, final Money money) {
        if (!otherCheckingAccount.getBankAccountType().equals(BankAccountType.CHECKING)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Transfer is only allowed between Checking Accounts");
        }
        BigDecimal finalBalanceAmountForOther = otherCheckingAccount.getBalance().getAmount().subtract(money.getAmount());
        this.setBalance(Money.euros(this.getBalance().getAmount().add(money.getAmount())));
        final Money finalBalanceForOther = Money.isGreaterThanZeroAmount(finalBalanceAmountForOther) ?
                Money.euros(finalBalanceAmountForOther) : Money.debtInEuros(finalBalanceAmountForOther);
        otherCheckingAccount.setBalance(finalBalanceForOther);
    }

    @Override
    public void calculateAndPayInterest(BankAccount bankAccountOther, Money initialAmount) {
        throw new UnsupportedOperationException("Error: Cannot calculate and pay interests in Checking Accounts");
    }

    @Override
    public void setBalance(final Money money) {
        if (this.getBalance() != null) {
            if (money.getAmount().compareTo(overdraftLimit) == -1) {
                throw new IllegalArgumentException(this.getClass().getSimpleName()
                        + ". Error: Checking account overdrawn, cannot transfer amount: " + money
                        + " due to overdraft limit: " + this.getOverdraftLimit());
            }
        }
        super.setBalance(money);
    }

    @Override
    public BigDecimal getOverdraftLimit() {
        return this.overdraftLimit;
    }

    @Override
    public void setOverdraftLimit(final BigDecimal overdraftLimit) {
        if (overdraftLimit == null || !Money.isGreaterThanZeroAmount(overdraftLimit) ||
                overdraftLimit.compareTo(new BigDecimal(5000)) == 1) {
            throw new IllegalArgumentException("Invalid overdraft limit: " + overdraftLimit);
        }
        this.overdraftLimit = overdraftLimit.negate();
    }
}

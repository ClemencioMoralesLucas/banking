package com.clemencio.morales.banking;

import com.clemencio.morales.model.Money;

import java.math.BigDecimal;

public abstract class BankAccount {

    public static final BigDecimal MAXIMUM_ALLOWED_AMOUNT = new BigDecimal(1_000_000);
    private String owner;
    private Money balance;
    private BankAccountType bankAccountType;

    public BankAccount(final String owner, final Money balance, final BankAccountType bankAccountType) {
        this.setOwner(owner);
        this.setBalance(balance);
        this.setBankAccountType(bankAccountType);
    }

    public void depositMoney(final Money money) {
        this.setBalance(Money.euros(this.getBalance().getAmount().add(money.getAmount())));
    }

    public void withdrawMoney(final Money money) {
        this.setBalance(Money.euros(this.getBalance().getAmount().subtract(money.getAmount())));
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public Money getBalance() {
        return this.balance;
    }

    public void setBalance(final Money balance) {
        if (Money.euros(MAXIMUM_ALLOWED_AMOUNT).getAmount().compareTo(balance.getAmount()) <= 0) {
            throw new IllegalArgumentException(MAXIMUM_ALLOWED_AMOUNT + " security threshold cannot be broken.");
        }
        this.balance = balance;
    }

    public BankAccountType getBankAccountType() {
        return this.bankAccountType;
    }

    public void setBankAccountType(final BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public boolean hasFunds() {
        return hasEnoughFundsComparedTo(Money.euros(new BigDecimal(0.01)));
    }

    public boolean hasEnoughFunds(final Money amount) {
        return hasEnoughFundsComparedTo(amount);
    }

    private boolean hasEnoughFundsComparedTo(final Money amount) {
        return !(this.getBalance().getAmount().compareTo(amount.getAmount()) == -1);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner='" + owner + '\'' +
                ", balance=" + balance +
                ", bankAccountType=" + bankAccountType +
                '}';
    }
}

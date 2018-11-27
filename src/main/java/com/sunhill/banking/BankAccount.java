package com.sunhill.banking;

import com.sunhill.model.Money;

import java.math.BigDecimal;

public abstract class BankAccount {

    private String owner;
    private Money balance;
    private BankAccountType bankAccountType;

    public BankAccount(final String owner, final Money balance, final BankAccountType bankAccountType) {
        this.setOwner(owner);
        this.setBalance(balance);
        this.setBankAccountType(bankAccountType);
    }

    public void depositMoney(final Money money) {
//        if(!isGreaterThanZeroAmount(amount)) { //TODO comprobar si traga dinero negativo, no debería
//            throw new IllegalArgumentException(this.getClass().getSimpleName()
//                    + ". Error: Amount to deposit cannot be negative");
//        }
        this.setBalance(Money.euros(this.getBalance().getAmount().add(money.getAmount())));
    }

    public void withdrawMoney(final Money money) {
//        if(!isGreaterThanZeroAmount(amount)) {//TODO comprobar si traga dinero negativo, no debería
//            throw new IllegalArgumentException(this.getClass().getSimpleName()
//                    + ". Error: Amount to withdraw cannot be negative");
//        }
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
        this.balance = balance;
    }

    public BankAccountType getBankAccountType() {
        return this.bankAccountType;
    }

    public void setBankAccountType(final BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public boolean hasFunds() {
        return hasEnoughFundsComparedTo(Money.euros(BigDecimal.ZERO));
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

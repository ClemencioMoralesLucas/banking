import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {

    private BigDecimal overdraftLimit;

    public CheckingAccount(final String owner, final BigDecimal balance, final BigDecimal overdraftLimit) {
        super(owner, balance, BankAccountType.CHECKING);
        this.setOverdraftLimit(overdraftLimit);
    }

    public void transferFromCheckingAccount(final BankAccount otherCheckingAccount, final BigDecimal amount) {
        if (!otherCheckingAccount.getBankAccountType().equals(BankAccountType.CHECKING)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Transfer is only allowed between Checking Accounts");
        }
        otherCheckingAccount.setBalance(otherCheckingAccount.getBalance().subtract(amount));
        this.setBalance(this.getBalance().add(amount));
    }

    @Override
    public void setBalance(final BigDecimal amount) {
        final BigDecimal possibleFinalBalance = this.getBalance().subtract(amount);
        if(possibleFinalBalance.compareTo(overdraftLimit) == -1) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Checking account overdrawn, cannot transfer amount");
        } else {
            super.setBalance(amount);
        }
    }

    public BigDecimal getOverdraftLimit() {
        return this.overdraftLimit;
    }

    public void setOverdraftLimit(final BigDecimal overdraftLimit) { //check is valid
        this.overdraftLimit = overdraftLimit;
    }
}

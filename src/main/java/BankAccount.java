import java.math.BigDecimal;

public abstract class BankAccount {

    private String owner;
    private BigDecimal balance;
    private BankAccountType bankAccountType;

    public BankAccount(final String owner, final BigDecimal balance, final BankAccountType bankAccountType) {
        this.setOwner(owner);
        this.setBalance(balance);
        this.setBankAccountType(bankAccountType);
    }

    public void deposit(final BigDecimal amount) {
        if(!isGreaterThanZeroAmount(amount)) { //TODO REFACTOR, SAME AS LINE 24
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Amount to deposit cannot be negative");
        }
        this.setBalance(balance.add(amount));
    }

    public void withdraw(final BigDecimal amount) {
        if(!isGreaterThanZeroAmount(amount)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Amount to withdraw cannot be negative");
        }
        setBalance(balance.subtract(amount));
    }

    boolean isGreaterThanZeroAmount(final BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 1;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public BankAccountType getBankAccountType() {
        return this.bankAccountType;
    }

    public void setBankAccountType(final BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
}

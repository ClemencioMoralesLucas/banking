import java.math.BigDecimal;

public class SavingAccount extends BankAccount {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private BigDecimal interestRate;

    public SavingAccount(final String owner, final BigDecimal balance, final BigDecimal interestRate) {
        super(owner, balance, BankAccountType.SAVING);
        this.setInterestRate(interestRate);
    }

    public void calculateAndPayInterest(final BigDecimal amount) {
        if(!super.isGreaterThanZeroAmount(amount)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ". Error: Amount to pay with interest cannot be negative");
        }
        this.setBalance(this.getBalance().add(amount).add(percentage(amount, interestRate)));
    }

    public static BigDecimal percentage(final BigDecimal base, final BigDecimal percentage){
        return base.multiply(percentage).divide(ONE_HUNDRED);
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(final BigDecimal interestRate) { //check is valid interestrate
        this.interestRate = interestRate;
    }
}

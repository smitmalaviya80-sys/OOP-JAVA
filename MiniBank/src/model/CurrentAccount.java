package model;

public class CurrentAccount extends Account {

    private long overdraftLimit;

    public CurrentAccount(
            String accountNumber,
            String holderName,
            long balance,
            long overdraftLimit) {

        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return balance - amount >= -overdraftLimit;
    }

    @Override
    public String toString() {
        return "Current Account | "
                + super.toString()
                + ", Overdraft Limit: " + overdraftLimit;
    }
}
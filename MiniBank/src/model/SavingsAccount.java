package model;

public class SavingsAccount extends Account {

    private long minBalance;

    public SavingsAccount(
            String accountNumber,
            String holderName,
            long balance,
            long minBalance) {

        super(accountNumber, holderName, balance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return balance - amount >= minBalance;
    }
}
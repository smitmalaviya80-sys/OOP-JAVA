package model;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(
            String accountNumber,
            String holderName,
            long balance) {

        super(accountNumber, holderName, balance);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }

    @Override
    public String toString() {
        return "Fixed Deposit Account | "
                + super.toString();
    }
}
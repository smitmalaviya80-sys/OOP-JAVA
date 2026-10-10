
package model;
import java.io.Serializable;
import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public abstract class Account implements Transactable, InterestBearing,Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    protected String accountNumber;

    @MaxLength(30)
    protected String holderName;

    @Positive
    protected long balance;

    public Account(String accountNumber, String holderName, long balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    @Override
    public synchronized void deposit(long amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero"
            );
        }

        balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero"
            );
        }

        if (!canWithdraw(amount)) {
            long shortfall = amount - balance;

            throw new InsufficientFundsException(
                    "Insufficient funds. Shortfall: " + shortfall,
                    shortfall
            );
        }

        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws BankException {
        try {
            withdraw(amount);
            to.deposit(amount);
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer operation completed.");
        }
    }

    @Override
    public long balance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {
        return accountNumber + " | " + holderName + " | " + balance;
    }

    @Override
    public double yearlyInterest() {
        return balance * interestRate() / 100;
    }
}
package model;

public interface InterestBearing {

    double interestRate();

    long balance();

    default double yearlyInterest() {
        return interestRate() * balance() / 100.0;
    }
}
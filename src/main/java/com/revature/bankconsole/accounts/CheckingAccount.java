package com.revature.bankconsole.accounts;

public class CheckingAccount {

    private double balance;
    private double interest;

    public CheckingAccount() {
        balance = 0;
    }

    public CheckingAccount(double initialBalance, double initialInterest) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public double getBalance() {
        return balance;
    }
}

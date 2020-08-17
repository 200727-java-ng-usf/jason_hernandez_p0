package com.revature.bankconsole.accounts;

public class CheckingAccount {

    private double balance;
    private double interest;

    public CheckingAccount() {
        balance = 0;
    }

    public CheckingAccount(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        if(balance - amount < 0) {
            System.out.println("Insufficient funds"); // My own code added
        } else {
            balance = balance - amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}

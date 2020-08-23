package com.revature.bankconsole.models;

public class AccountInfo {

    // Set up fields
    private Integer accountNumber;
    private Float balance;

    public AccountInfo(){}

    // Basic registration: assign an account number
    public AccountInfo(Integer accountNumber) {
        this.accountNumber = accountNumber;

    }

    // Adds the balance
    public AccountInfo(Float balance, Integer accountNumber) {
        this(accountNumber);
        this.balance = balance;
    }

    // Makes a copy (for thread safety)
    public AccountInfo(AccountInfo copy) {
        this(copy.balance, copy.accountNumber);
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}

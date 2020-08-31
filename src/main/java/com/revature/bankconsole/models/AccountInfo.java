package com.revature.bankconsole.models;

import java.util.Objects;

public class AccountInfo {

    // Set up fields
    private Integer accountNumber;
    private Float balance;
    static Integer count = 1;

    public AccountInfo(){
        UserInfo userInfo = new UserInfo();
    }


    // Adds the balance
    public AccountInfo(Float balance, Integer accountNumber) {
        this.accountNumber = accountNumber;
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

    public AccountInfo getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public boolean deposit(float amount) {
        if(amount < 0) {
            System.out.println("Cannot deposit a negative");
        } else {
            balance += amount;

        } return true;
    }

    public boolean withdrawal(float amount) {
        if(amount < 0) {
            System.out.println("Cannot withdraw a negative");
        } else if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
        } return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountInfo)) return false;
        AccountInfo that = (AccountInfo) o;
        return Float.compare(balance, that.balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance);
    }

    @Override
    public String toString() {
        return " ";
    }
}

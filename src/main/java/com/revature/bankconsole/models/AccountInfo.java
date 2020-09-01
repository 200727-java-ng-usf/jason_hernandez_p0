package com.revature.bankconsole.models;

import com.revature.bankconsole.exceptions.OverdraftException;

import java.util.Objects;

public class AccountInfo {

    // Set up fields
    private Integer accountNumber;
    private Float balance;
    static Integer count = 1;

    // Bring a customer to the account

    public AccountInfo(){

        UserInfo userInfo = new UserInfo();
    }


    // Adds the balance
    public AccountInfo(Float balance, Integer accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountInfo(Float balance) {
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

    public boolean deposit(float amount) {
        balance += amount;
        return true;
    }

    public boolean withdrawal(float amount) throws OverdraftException {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            throw new OverdraftException("Insufficient funds!");
        }
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
        return Objects.hash(getBalance());
    }

    @Override
    public String toString() {
        return " ";
    }
}

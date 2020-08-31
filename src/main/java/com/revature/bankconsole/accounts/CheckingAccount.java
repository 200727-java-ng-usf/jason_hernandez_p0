package com.revature.bankconsole.accounts;

import com.revature.bankconsole.models.UserInfo;

import java.util.Objects;

public class CheckingAccount {

    private float balance;
    float amount = 0;
    int checkingNumber;

    public CheckingAccount() {

        UserInfo userInfo = new UserInfo();
    }

    public CheckingAccount(int checkingNumber, float balance) {
        this.checkingNumber = checkingNumber;
        this.balance = balance;
    }

    public CheckingAccount(float balance) {
        this.balance = balance;
    }

    public boolean deposit(float amount) {
        balance += amount;
        return true;
    }

    public boolean withdrawal(float amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds!");
        } return false;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof CheckingAccount)) return false;
        CheckingAccount account = (CheckingAccount) o;
        return Float.compare(account.getBalance(), getBalance()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance());
    }


    @Override
    public String toString() {
        return " ";
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getAccountNo() {
        return checkingNumber;
    }

    public void setAccountNo(int accountNo) {
        this.checkingNumber = accountNo;
    }


}

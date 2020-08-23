package com.revature.bankconsole.accounts;

public class UserAccounts {
    int savingsNumber;
    int checkingNumber;

    double savingsBalance = SavingsAccount.getBalance();
    double checkingBalance = CheckingAccount.getBalance();

    public Integer getSavingsNumber() {
        return savingsNumber;
    }

    public void setSavingsNumber(int savingsNumber) {
        this.savingsNumber = savingsNumber;
    }

    public Integer getCheckingNumber() {
        return checkingNumber;
    }

    public void setCheckingNumber(int checkingNumber) {
        this.checkingNumber = checkingNumber;
    }

    public float getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public float getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }
}

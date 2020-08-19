package com.revature.bankconsole.accounts;

public class UserAccounts {
    int savingsNumber;
    int checkingNumber;

    double savingsBalance = SavingsAccount.getBalance();
    double checkingBalance = CheckingAccount.getBalance();
}

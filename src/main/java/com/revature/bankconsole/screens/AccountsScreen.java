package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AccountsScreen extends Screen{

    private UserAccounts userAccounts;

    public AccountsScreen(UserAccounts userAccounts) {
        System.out.println("[LOG]");
        this.userAccounts = userAccounts;
    }

    @Override
    public String render() {

        // Show user accounts
        if(savingsNumber) {
            System.out.println("Your savings account number" + savingsNumber);
            System.out.println("balance is " + savingsBalance);
        }
        if(checkingNumber) {
            System.out.println("Your checking account number" + checkingNumber);
            System.out.println("balance is " + checkingBalanceBalance);
        }

        // Allow deposits and withdrawals
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Please select an account.  ");
            String account = console.readLine();

            System.out.println("You entered: " + account);
            return account;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            System.out.print("Do you want to deposit or withdraw?  ");
            String transType = console.readLine();

            System.out.println("You entered: " + transType);
            return transType;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return;
    }
}

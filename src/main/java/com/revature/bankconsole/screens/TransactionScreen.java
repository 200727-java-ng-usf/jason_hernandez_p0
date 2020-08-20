package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TransactionScreen extends Screen {

    private UserAccounts userAccounts;

    public TransactionScreen(UserAccounts userAccounts) {
        this.userAccounts = userAccounts;
        System.out.println("Your accounts are " + userAccounts);
    }

    @Override
    public String render() {

        // Allow deposits and withdrawals
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Please select an account.  ");
            String account = console.readLine();

            System.out.println("You entered: " + account);
            return account;

        } catch (
                IOException ioe) {
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

        return userAccounts;
    }
}

package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.bankconsole.AppDriver.app;

public class TransactionScreen extends Screen {

    private UserAccounts userAccounts;

    public TransactionScreen(UserAccounts userAccounts) {
        super("TransactionScreen", "/transaction");
        this.userAccounts = userAccounts;
    }

    @Override
    public void render() {

        // Must use wrapper class to do a null check
        Integer accountNumber = userAccounts.getAccountNumber();


        // Show only accounts that the user has
        System.out.println("Your accounts are:");
        if (accountNumber != null) {
            System.out.println("Savings: " + accountNumber);
        }


        // Allow deposits and withdrawals
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Please select an account.  ");
            Integer account = console.read();

            System.out.println("You entered: " + account);
            if (account.equals(accountNumber)) {
                app.getRouter().navigate("/checking");
//            } else if (account.equals(savingsAccountNumber)) {
//                app.getRouter().navigate("/savings");
            } else {
                System.out.println("That is not a valid account number");
                    app.getRouter().navigate("/transaction");
            }

        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

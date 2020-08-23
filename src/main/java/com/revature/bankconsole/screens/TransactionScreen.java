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
        Integer savingsAccountNumber = userAccounts.getSavingsNumber();
        Integer checkingAccountNumber = userAccounts.getCheckingNumber();

        // Show only accounts that the user has
        System.out.println("Your accounts are:");
        if (savingsAccountNumber != null) {
            System.out.println("Savings: " + savingsAccountNumber);
        }
        if(checkingAccountNumber != null) {
            System.out.println("Checking: " + checkingAccountNumber);
        }

        // Allow deposits and withdrawals
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Please select an account.  ");
            Integer account = console.read();

            System.out.println("You entered: " + account);
            if (account.equals(savingsAccountNumber)) {
                app.getRouter().navigate("/savings");
            } else if (account.equals(checkingAccountNumber)) {
                app.getRouter().navigate("/checking");
            } else {
                System.out.println("That is not a valid account number");
                    app.getRouter().navigate("/transaction");
            }

        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            System.out.print("Do you want to deposit or withdraw?  ");
            String transType = console.readLine();

            System.out.println("You entered: " + transType);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

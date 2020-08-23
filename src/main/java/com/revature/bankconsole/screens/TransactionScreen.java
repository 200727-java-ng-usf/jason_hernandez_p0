package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TransactionScreen extends Screen {

    private UserAccounts userAccounts;

    public TransactionScreen(UserAccounts userAccounts) {
        super("TranscationScreen", "/transaction");
        this.userAccounts = userAccounts;
        Integer savingsAccountNumber = userAccounts.getSavingsNumber();
        Integer checkingAccountNumber = userAccounts.getCheckingNumber();
        System.out.println("Your accounts are:");
        if (savingsAccountNumber != null) {
            System.out.println("Savings: " + savingsAccountNumber);
        }
        if(checkingAccountNumber != null) {
            System.out.println("Checking: " + checkingAccountNumber);
        }
    }

    @Override
    public void render() {

        // Allow deposits and withdrawals
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Please select an account.  ");
            String account = console.readLine();

            System.out.println("You entered: " + account);

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

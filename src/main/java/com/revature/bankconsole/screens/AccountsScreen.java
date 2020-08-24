package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

import static com.revature.bankconsole.AppDriver.app;

public class AccountsScreen extends Screen {

    private UserAccounts userAccounts;

    public AccountsScreen(UserAccounts userAccounts) {
        super("AccountsScreen", "/accounts");
        this.userAccounts = userAccounts;
    }

    @Override
    public void render() {

        // Must use wrapper class to do a null check
        Integer savingsAccountNumber = userAccounts.getSavingsNumber();
        Integer checkingAccountNumber = userAccounts.getCheckingNumber();
        float savingsAccountBalance = userAccounts.getSavingsBalance();
        float checkingAccountBalance = userAccounts.getCheckingBalance();

        // Show only accounts that the user has
        System.out.println("Your accounts are:");
        if (savingsAccountNumber != null) {
            System.out.println("Savings: " + savingsAccountNumber);
            System.out.println("balance: $" + savingsAccountBalance);
        }
        if (checkingAccountNumber != null) {
            System.out.println("Checking: " + checkingAccountNumber);
            System.out.println("balance: $" + checkingAccountBalance);
        }

        try {
            System.out.println("Do you want to make a transaction? y/n");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "y":
                    app.getRouter().navigate("/transaction");
                    break;
                case "n":
                    app.getRouter().navigate("/dashboard");
                    break;
                default:
                    System.out.println("[LOG] - Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



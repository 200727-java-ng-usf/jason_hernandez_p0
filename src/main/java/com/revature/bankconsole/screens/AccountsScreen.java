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
        Integer accountNumber = userAccounts.getAccountNumber();
        float accountBalance = userAccounts.getAccountBalance();

        // Show only accounts that the user has
        System.out.println("Your accounts are:");
        if (accountNumber != null) {
            System.out.println("Account number: " + accountNumber);
            System.out.println("balance: $" + accountBalance);
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
                    System.out.println("404 - Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



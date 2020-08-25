package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.accounts.UserAccounts;

import static com.revature.bankconsole.AppDriver.app;

public class CheckingTransactionScreen extends Screen{

    private UserAccounts userAccounts;

    public CheckingTransactionScreen(UserAccounts userAccounts) {
        super("CheckingTransactionScreen", "/checking");
        this.userAccounts = userAccounts;
    }

    @Override
    public void render() {

        float amount = 0;

        System.out.println("Select 1 for deposit, 2 for withdrawal");

        try {
            System.out.print("> ");
            Integer userSelection = app.getConsole().read();

            switch (userSelection) {
                case 1:

                    System.out.print("Enter the deposit amount: $");
                    amount = app.getConsole().read();
                    //return amount;
                break;
                case 2:

                    System.out.print("Enter the withdrawal amount: $");
                    amount = app.getConsole().read();
                    //return amount;
                break;
                default:
                    System.out.println("[LOG] - Invalid selection!");
                    app.getRouter().navigate("/savings");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}


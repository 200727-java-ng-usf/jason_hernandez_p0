package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

import static com.revature.bankconsole.AppDriver.app;

public class AccountsScreen extends Screen{

    public AccountsScreen() {
        super("HomeScreen", "/accounts");
        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {

        System.out.println("Your accounts:");
        // Show user accounts
        if(savingsNumber) {
            System.out.println("Your savings account number" + savingsNumber);
            System.out.println("balance is " + savingsBalance);
        }
        if(checkingNumber) {
            System.out.println("Your checking account number" + checkingNumber);
            System.out.println("balance is " + checkingBalance);
        }
        System.out.println("+--------------------------+");
        System.out.println("1) Make a transaction");
        System.out.println("2) Exit Application");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/transaction");
                    break;
                case "2":
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.out.println("Invalid selection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//    public AccountsScreen() {
//        super("SearchBooksScreen", "/search");
//        System.out.println("[LOG] - Instantiating " + super.getName());
//    }
//
//    private UserAccounts userAccounts;
//
//    public AccountsScreen(UserAccounts userAccounts) {
//        System.out.println("Your accounts are " + userAccounts);
//        this.userAccounts = userAccounts;
//    }
//
//    @Override
//    public String render() {
//
//
//
//
//        return null;
//    }
}

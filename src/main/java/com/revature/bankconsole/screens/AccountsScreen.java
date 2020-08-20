package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.UserAccounts;

public class AccountsScreen extends Screen{

    private UserAccounts userAccounts;

    public AccountsScreen(UserAccounts userAccounts) {
        System.out.println("Your accounts are " + userAccounts);
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


        return null;
    }
}

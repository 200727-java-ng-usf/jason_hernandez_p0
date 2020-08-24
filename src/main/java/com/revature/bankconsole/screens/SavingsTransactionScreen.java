package com.revature.bankconsole.screens;

import com.revature.bankconsole.accounts.SavingsAccount;
import com.revature.bankconsole.accounts.UserAccounts;

import static com.revature.bankconsole.AppDriver.app;

public class SavingsTransactionScreen extends Screen{

    private UserAccounts userAccounts;

    public SavingsTransactionScreen(UserAccounts userAccounts) {
        super("SavingsTransactionScreen", "/savings");
        this.userAccounts = userAccounts;
    }

    @Override
    public void render() {

        System.out.println("Select 1 for deposit, 2 for withdrawal");

            try {
                System.out.print("> ");
                Integer userSelection = app.getConsole().read();

                switch (userSelection) {
                    case 1:
                        float depositAmount;
                        System.out.print("Enter the deposit amount: $");
                        depositAmount = app.getConsole().read();
                        return depositAmount;
                        break;
                    case 2:
                        float withdrawalAmount;
                        System.out.print("Enter the withdrawal amount: $");
                        withdrawalAmount = app.getConsole().read();
                        return withdrawalAmount;
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


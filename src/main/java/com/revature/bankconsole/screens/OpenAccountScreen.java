package com.revature.bankconsole.screens;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.services.AccountServices;
import com.revature.bankconsole.utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.bankconsole.AppDriver.app;

public class OpenAccountScreen extends Screen {

    private AccountServices accountServices;

    public OpenAccountScreen(AccountServices accountServices) {
        super("OpenAccountScreen", "/newaccount");
        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {

        Float balance;


        System.out.println("What kind of account do you want to open?");
//        System.out.println("1) Savings account");
        System.out.println("2) Checking account");
        System.out.println("3) Logout");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
//                case "1":
//                    break;
                case "2":
                    balance = 0.00f;
                    AccountInfo newAccount = new AccountInfo();

                    accountServices.register(newAccount);
                    break;
                case "3":
                    app.setCurrentUser(null);
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.out.println("[404] - Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

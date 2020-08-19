package com.revature.bankconsole;

import com.revature.bankconsole.repos.UserRepo;
import com.revature.bankconsole.screens.AccountsScreen;
import com.revature.bankconsole.screens.LoginScreen;
import com.revature.bankconsole.screens.RegistrationScreen;
import com.revature.bankconsole.screens.TransactionScreen;
import com.revature.bankconsole.services.UserServices;
import com.revature.bankconsole.accounts.UserAccounts;

public class AppDriver {

    public static <RegisterScreen> void main(String[] args) {

        // Initialize first objects
        UserRepo userRepo = new UserRepo();
        UserServices userServices = new UserServices(userRepo);

        // If existing user, display login screen
        LoginScreen loginScreen = new LoginScreen(userServices);
        loginScreen.render();

        // If new user, display registration screen
        RegistrationScreen registrationScreen = new RegistrationScreen(userServices);
        registrationScreen.render();

        // Display accounts
        AccountsScreen accountsScreen = new AccountsScreen(userAccounts);
        accountsScreen.render();

        // Allow transactions
        TransactionScreen transactionScreen = new TransactionScreen(userAccounts);
        transactionScreen.render();

    }
}



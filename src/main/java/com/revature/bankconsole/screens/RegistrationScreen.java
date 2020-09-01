package com.revature.bankconsole.screens;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.CheckingRepo;
import com.revature.bankconsole.repos.UserRepo;
import com.revature.bankconsole.services.UserServices;
import com.revature.bankconsole.utilities.AccountNumberGenerator;

import java.io.IOException;

import static com.revature.bankconsole.AppDriver.app;

public class RegistrationScreen extends Screen {

    private UserServices userService;
    private CheckingRepo checkingRepo;
    private UserRepo userRepo;


    public RegistrationScreen() {
        super("RegisterScreen", "/register");
        userService = new UserServices();
        checkingRepo = new CheckingRepo();
        userRepo = new UserRepo();
    }

    @Override
    public void render() {
        // Take user input
        String firstname, lastname, username, password, email;

        try {
            System.out.println("Sign up for a new account.");
            System.out.print("first name: ");
            firstname = app.getConsole().readLine();
            System.out.print("last name: ");
            lastname = app.getConsole().readLine();
            System.out.println("Email: ");
            email = app.getConsole().readLine();

            System.out.print("Username: ");
            username = app.getConsole().readLine();

            while(userRepo.findUserByUsername(username).isPresent()==true) {
                System.out.println("That username already exists");
                System.out.println("Pleas choose a different username");
                username = app.getConsole().readLine();
            }
            System.out.println("Password: ");
            password = app.getConsole().readLine();

            // All the above inputs go into this new user profile.
            UserInfo newUser = new UserInfo(firstname, lastname, email, username, password);

            Integer newAccount;
            boolean hasAccount = true;
            while (hasAccount) {
                newAccount= AccountNumberGenerator.newAccountNumber();
                hasAccount = checkingRepo.findAccountByNumber(newAccount).isPresent();
            }
            newUser.setAccountNumber(AccountNumberGenerator.newAccountNumber());

            userService.register(newUser);
            System.out.println("New profile created successfully");

            if (app.isSessionValid()) {
                app.getRouter().navigate("/home");
            }

        } catch (IOException ioe) {
            System.out.println("Error: failed to register");
            app.getRouter().navigate("/register");
        }
    }
}

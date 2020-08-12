package com.revature.bankconsole.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistrationScreen extends Screen {

    private UserService userService;

    public RegistrationScreen(UserService userService) {
        System.out.println("[LOG]");
        this.userService = userService;
    }

    @Override
    public void render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String firstname, lastname, username, password;

        try {
            System.out.println("Sign up for a new account.");
            System.out.print("first name: ");
            firstname = console.readLine();
            System.out.print("last name: ");
            lastname = console.readLine();
            System.out.print("Username: ");
            username = console.readLine();
            System.out.println("Password: ");
            password = console.readLine();

            AppUser newUser = new AppUser(firstname, lastname, username, password);
            AppUser registeredUser = userService.register(newUser);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

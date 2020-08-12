package com.revature.bankconsole;

import com.revature.bankconsole.repos.UserRepo;
import com.revature.bankconsole.screens.LoginScreen;
import com.revature.bankconsole.screens.RegistrationScreen;
import com.revature.bankconsole.services.UserServices;

public class AppDriver {

    public static <RegisterScreen> void main(String[] args) {

        UserRepo userRepo = new UserRepo();
        UserServices userServices = new UserServices(userRepo);

        LoginScreen loginScreen = new LoginScreen(userServices);
        loginScreen.render();

        RegistrationScreen registrationScreen = new RegistrationScreen(userServices);
        registrationScreen.render();
    }
}



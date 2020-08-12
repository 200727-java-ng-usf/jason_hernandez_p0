package com.revature.bankconsole;

public class AppDriver {

    public static void main(String[] args) {

//        UserRepository userRepo = new UserRepository();
//        UserService userService = new UserService(userRepo);

        LoginScreen loginScreen = new LoginScreen(userService);
        loginScreen.render();

        RegisterScreen registerScreen = new RegisterScreen(userService);
        registerScreen.render();
    }
}

}

package com.revature.bankconsole.screens;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.services.AccountServices;
import com.revature.bankconsole.services.UserServices;

import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.bankconsole.AppDriver.app;

public class DashboardScreen extends Screen {

    LoginScreen loginScreen;
    UserInfo authUser;
    private AccountServices accountServices;
    private UserServices userServices;

    // This puts it into currency format
    NumberFormat us   = NumberFormat.getCurrencyInstance(Locale.US);

    public DashboardScreen() {
        super("DashboardScreen", "/dashboard");

    }

    public void setAuthUser(UserInfo authUser) {
        this.authUser = authUser;
        accountServices.setAuthUser(authUser);
    }

    // Let the user verify the info before proceeding
    public double showBalance(){
        return authUser.getAccount().getBalance();
    }
    public String printName(){
        return authUser.getFirstName() + "." + authUser.getLastName();
    }
    public Integer PrintAccNo(){return authUser.getAccount().getAccountNumber();}

    @Override
    public void render() {
        System.out.println("Welcome to your dashboard, " + this.printName());
        System.out.println("Your balance is: " + us.format(this.showBalance()));
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Logout");


        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    accountServices.deposit();
                    break;
                case "2":
                    accountServices.withdrawal();
                    break;
                case "3":
                    app.setCurrentUser(null);
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.out.println("Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

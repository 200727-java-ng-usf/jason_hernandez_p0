package com.revature.bankconsole.screens;
import static com.revature.bankconsole.AppDriver.app;

public class DashboardScreen extends Screen {

    public DashboardScreen() {
        super("DashboardScreen", "/dashboard");
        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {
        System.out.println("Welcome to your dashboard!");
        System.out.println("1) View accounts");
        System.out.println("2) Open a new account");
        System.out.println("3) Logout");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/accounts");
                    break;
                case "2":
                    app.getRouter().navigate("/newaccount");
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

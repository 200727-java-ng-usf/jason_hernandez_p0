package com.revature.bankconsole.services;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.CheckingRepo;

import java.text.NumberFormat;
import java.util.*;

// import com.revature.bankconsole.repos.SavingsRepo;

public class AccountServices {

    private UserServices userServices;
    private CheckingRepo checkingRepo;

    private UserInfo authUser;
    NumberFormat us   = NumberFormat.getCurrencyInstance(Locale.US);   // print USD format


    public AccountServices() {
        userServices = new UserServices();
        checkingRepo = new CheckingRepo();
    }

    // Sets the current user in this class
    public void setAuthUser(UserInfo authUser) {

        this.authUser = authUser;
    }

    // Make deposits here instead of a separate transaction screen
    public void deposit() {
        float amount = 0f;
        Scanner sc = new Scanner(System.in);
        System.out.println(" Please enter the amount of the deposit.");

        try {
            amount = sc.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input -- please enter a number.");
        }

        boolean successful = false;
        if (amount > 0) {
            successful = authUser.getAccount().deposit(amount);
        } else successful = false;

        if (successful) {
            System.out.println("You deposited: " + us.format(amount));
            System.out.println("Your new balance is: " + us.format(authUser.getAccount().getBalance()));
            update(authUser.getAccountNumber(),authUser.getAccount().getBalance());
        } else {
            System.out.println("Error - transaction unsuccessful");
        }
    }

    // Essentially the same for withdrawals
    // Make deposits here instead of a separate transaction screen
    public void withdrawal() {
        float amount = 0f;
        Scanner sc = new Scanner(System.in);
        System.out.println(" Please enter the amount of the deposit.");

        try {
            amount = sc.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input -- please enter a number.");
        }

        boolean successful = false;
        if (amount > 0) {
            successful = authUser.getAccount().deposit(amount);
        } else successful = false;

        if (successful) {
            System.out.println("You withdrew: $" + amount);
            System.out.println("Your new balance is: $ " + us.format(authUser.getAccount().getBalance()));
            update(authUser.getAccountNumber(),authUser.getAccount().getBalance());
        } else {
            System.out.println("Error - transaction unsuccessful");
        }
    }


    public Set<AccountInfo> getAccountById() {
        return new HashSet<>();
    }

    public void update(Integer accountNo, Float balance){
        checkingRepo.updateAccount(accountNo,balance);
    }
}

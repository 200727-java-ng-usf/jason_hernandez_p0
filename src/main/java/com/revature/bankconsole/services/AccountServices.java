package com.revature.bankconsole.services;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.exceptions.AuthenticationException;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.CheckingRepo;
// import com.revature.bankconsole.repos.SavingsRepo;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class AccountServices {

    private UserServices userServices;
    private CheckingRepo checkingRepo;

    private UserInfo authUser;

    AccountServices() {
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
            System.out.println("You deposited: $" + amount);
            System.out.println("Your new balance is: $ " + authUser.getAccount().getBalance());
            update(authUser.getAccount().getBalance());
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
            System.out.println("Your new balance is: $ " + authUser.getAccount().getBalance());
            update(authUser.getAccount().getBalance());
        } else {
            System.out.println("Error - transaction unsuccessful");
        }
    }


    public Set<AccountInfo> getAccountById() {
        return new HashSet<>();
    }

    public boolean update(AccountInfo updatedAccount) {
        return false;
    }
}

package com.revature.bankconsole.accounts;

import java.util.Scanner;  // Import the Scanner class

public class InterestCalculator {
    public float calculateTheInterest() {

        // Online bank accounts have higher interest rates than conventional
        // 1-2% is a good rate for an online account
        float interestRate = (float) 0.15;

        // Access savings account balance
        float amtPrincipal = SavingsAccount.balance;

        Scanner howLong = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Your principal was " + amtPrincipal + " and your interest rate is " + interestRate);
        System.out.println("Enter the number of years ");
        float amtTime = howLong.nextFloat();  // Read user input

        // Calculate the interest
        float interest = amtPrincipal * interestRate * amtTime;
        System.out.println("Interest earned this period is: " + interest);  // Output calculated amount

        return interest;
    }

}



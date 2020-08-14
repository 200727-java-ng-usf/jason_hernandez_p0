package com.revature.bankconsole.calculations;

import java.util.Scanner;  // Import the Scanner class

public class InterestCalculator {
    public float calculateTheInterest() {

        // Begin by taking input
        Scanner principal = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter principal ");
        float amtPrincipal = principal.nextFloat();  // Read user input

        Scanner rate = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter interest rate ");
        float interestRate = rate.nextFloat();  // Read user input

        Scanner howLong = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the amount of time ");
        float amtTime = howLong.nextFloat();  // Read user input

        // Calculate the interest
        float interest = amtPrincipal * interestRate * amtTime;
        System.out.println("Interest earned this period is: " + interest);  // Output calculated amount

        return interest;
    }

}



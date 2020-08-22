package com.revature.bankconsole.accounts;

public class SavingsAccount {

    //SavingsAccount.java - Jimmy Kurian (GitHUb user)

        private double balance;
        private double interest;

        public SavingsAccount() {
            balance = 0;
            interest = 0;
        }

        public SavingsAccount(double initialBalance, double initialInterest) {
            balance = initialBalance;
            interest = initialInterest;
        }

        public void deposit(double amount) {

            // Kurian's code did not include prevention of negative amounts
            if (amount < 0) {
                System.out.println("Invalid amount");
            }
            balance = balance + amount;
            System.out.println("Your new balance is " + balance);
        }

        public void withdraw(double amount) {
            // No negative amounts
            if (amount < 0) {
                System.out.println("Invalid amount");
            }

            // Kurian's code also did not include overdraft prevention
            if(balance - amount < 0) {
                System.out.println("Insufficient funds");
            } else {
                balance = balance - amount;
                System.out.println("Your remaining balance is " + balance);
            }
        }

        public void addInterest() {
            balance = balance + balance * interest;
        }

        public static double getBalance() {
            return 0;
        }

    }


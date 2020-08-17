package com.revature.bankconsole.accounts;

class SavingsAccount {

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
            balance = balance + amount;
        }

        public void withdraw(double amount) {
            if(balance - amount < 0) {
                System.out.println("Insufficient funds"); // My own code added
            } else {
                balance = balance - amount;
            }
        }

        public void addInterest() {
            balance = balance + balance * interest;
        }

        public double getBalance() {
            return balance;
        }

    }


package com.revature.bankconsole.accounts;

public class SavingsAccount {

    float balance = 0;
    float amount = 0;
    float interest = 0;

    public void transactionType (String transaction) {

        // Prevention of negative deposits/withdrawals
        if (amount < 0) {
            System.out.println("Invalid amount");
        }

        switch (transaction) {
            case "deposit":

                balance = balance + amount;

            case "withdrawal":
                if (balance - amount < 0) {
                    System.out.println("Insufficient funds"); // My own code added
                } else {
                    balance = balance - amount;
                }
        }
    }
        public void addInterest() {
            balance = balance + balance * interest;
        }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }
}


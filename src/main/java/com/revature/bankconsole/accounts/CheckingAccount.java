package com.revature.bankconsole.accounts;

public class CheckingAccount {

    float balance = UserAccounts.getCheckingBalance();
    float amount = 0;

    public void transactionType(String transaction) {

        // Prevention of negative deposits/withdrawals
        if (amount < 0) {
            System.out.println("Invalid amount");
        }

        // Two possibilities
        switch (transaction) {
            case "deposit":

                balance = balance + amount; // add deposit to balance

            case "withdrawal":
                if (balance - amount < 0) { // This if statement prevents overdrafts
                    System.out.println("Insufficient funds"); // My own code added
                } else {
                    balance = balance - amount; // subtract withdrawal from balance
                }
        }
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


}

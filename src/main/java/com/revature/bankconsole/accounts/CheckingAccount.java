package com.revature.bankconsole.accounts;

public class CheckingAccount {

    public void transactionType (String transaction) {

        float balance = 0;
        float amount = 0;

        // Prevention of negative deposits/withdrawals
        if (amount < 0) {
            System.out.println("Invalid amount");
        }

        switch(transaction) {
            case "deposit":

                balance = balance + amount;

            case "withdrawal":
                if(balance - amount < 0) {
                    System.out.println("Insufficient funds"); // My own code added
                } else {
                    balance = balance - amount;
                }
            }
        }

}

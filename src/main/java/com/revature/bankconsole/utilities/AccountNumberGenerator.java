package com.revature.bankconsole.utilities;

import java.util.Random;

public class AccountNumberGenerator {

    public static Integer newAccountNumber()
    {
        Random rand = new Random();
        Integer newAccountNumber = 100;
        for (int i = 0; i < 3; i++)
        {
            int n = rand.nextInt(10) + 0;
            newAccountNumber += new Integer(n);
        }
        return newAccountNumber;
    }

}

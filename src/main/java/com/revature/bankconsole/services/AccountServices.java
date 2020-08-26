package com.revature.bankconsole.services;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.repos.CheckingRepo;
import com.revature.bankconsole.repos.SavingsRepo;

import java.util.HashSet;
import java.util.Set;

public class AccountServices {

    private SavingsRepo savingsRepo;

    public AccountServices(SavingsRepo sRepo) {
        savingsRepo = sRepo;
    }

    private CheckingRepo checkingRepo;

    public AccountServices(CheckingRepo cRepo) {
        System.out.println("[LOG] - Instantiating" + this.getClass().getName());
        checkingRepo = cRepo;
    }

    public void register(AccountInfo newAccount) {

        AccountInfo registeredAccount = checkingRepo.save(newAccount);
    }

    public Set<AccountInfo> getAllAccounts() {
        return new HashSet<>();
    }

    public Set<AccountInfo> getAccountById() {
        return new HashSet<>();
    }
    public Set<AccountInfo> getAccountByUsername() {
        return new HashSet<>();
    }

    public boolean update(AccountInfo updatedAccount) {
        return false;
    }
}

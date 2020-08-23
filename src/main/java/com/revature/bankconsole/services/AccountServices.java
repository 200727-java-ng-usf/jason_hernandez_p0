package com.revature.bankconsole.services;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.repos.AccountRepo;

import java.util.HashSet;
import java.util.Set;

public class AccountServices {

    private AccountRepo accountRepo;

    public AccountServices(AccountRepo aRepo) {
        System.out.println("[LOG] - Instantiating" + this.getClass().getName());
        accountRepo = aRepo;
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

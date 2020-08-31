package com.revature.bankconsole.services;

import com.revature.bankconsole.accounts.CheckingAccount;
import com.revature.bankconsole.exceptions.AuthenticationException;
import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.repos.CheckingRepo;
// import com.revature.bankconsole.repos.SavingsRepo;

import java.util.HashSet;
import java.util.Set;

public class AccountServices {

//    private SavingsRepo savingsRepo;
//
    public AccountServices(CheckingRepo cRepo) {
        checkingRepo = cRepo;
    }

    private CheckingRepo checkingRepo;

    public CheckingAccount AccountServices(Integer user_id) {
        if(user_id == null) {
            throw new RuntimeException("404 - No account found!");
        }
        CheckingAccount currentAccount = checkingRepo.findBalance(user_id)
                .orElseThrow(AuthenticationException::new);

//        app.(currentAccount);
        return currentAccount;
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

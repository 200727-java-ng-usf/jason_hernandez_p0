package com.revature.bankconsole.services;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.repos.AccountRepo;
import com.revature.bankconsole.repos.CheckingRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class AccountServicesTest {

    private AccountServices sut;
    private CheckingRepo mockAccountRepo = Mockito.mock(CheckingRepo.class);
    Set<AccountInfo> mockAccounts = new HashSet<>();

    @Before
    public void setup() {
        sut = new AccountServices(mockAccountRepo);
        mockAccounts.add(new AccountInfo((float) 4321.89, 1));
        mockAccounts.add(new AccountInfo((float) 999.99,2));

    }

    @After
    public void tearDown() {
        sut = null;
        mockAccounts.removeAll(mockAccounts);
    }

    @Test
    public void findNonExistentAccount() {
        AccountInfo expectedAccount = new AccountInfo((float) 6789.02, 8);
        Mockito.when(mockAccountRepo.findAccountByNumber(Mockito.anyInt()))
                .thenReturn(java.util.Optional.of(expectedAccount));
        AccountInfo actualResult = (AccountInfo) sut.getAccountById();
        Assert.assertEquals(expectedAccount, actualResult);
    }

    @Test
    public void findExistingAccount() {
        AccountInfo expectedAccount = new AccountInfo((float) 4321.89, 1);
        Mockito.when(mockAccountRepo.findAccountByNumber(Mockito.anyInt()))
                .thenReturn(java.util.Optional.of(expectedAccount));
        AccountInfo actualResult = (AccountInfo) sut.getAccountById();
        Assert.assertEquals(expectedAccount, actualResult);
    }
}

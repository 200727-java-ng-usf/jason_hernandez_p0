package com.revature.bankconsole.services;

import com.revature.bankconsole.models.AccountInfo;
import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.CheckingRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

public class AccountServicesTest {

    private AccountServices sut;
    private CheckingRepo mockAccountRepo = mock(CheckingRepo.class);
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

//    @Test
//    public void findNonExistentAccount() {
////        AccountInfo actualResult = (AccountInfo) sut.getAccountById();
//        AccountInfo actualResult;
//        AccountInfo expectedAccount = new AccountInfo((float) 6789.02, 8);
//        when(mockAccountRepo.findAccountByNumber(8))
//                .thenReturn(java.util.Optional.of(actualResult = expectedAccount));
//        Assert.assertEquals(expectedAccount, actualResult);
//    }
//
//    @Test
//    public void findExistingAccount() {
//        AccountInfo actualResult = (AccountInfo) sut.getAccountById();
//        AccountInfo expectedAccount = new AccountInfo((float) 4321.89, 1);
//        when(mockAccountRepo.findAccountByNumber(anyInt()))
//                .thenReturn(java.util.Optional.of(expectedAccount));
//        Assert.assertEquals(expectedAccount, actualResult);
//    }
}

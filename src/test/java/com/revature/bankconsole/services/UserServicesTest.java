package com.revature.bankconsole.services;

import com.revature.bankconsole.models.UserInfo;
import com.revature.bankconsole.repos.UserRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class UserServicesTest {

    private UserServices sut;
    private UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
    Set<UserInfo> mockUsers = new HashSet<>();

    @Before
    public void setup() {
        sut = new UserServices(mockUserRepo);
        mockUsers.add(new UserInfo(1, "Adam", "Inn", "admin", "p4ssw0rd", "admin@revbooks.com"));
        mockUsers.add(new UserInfo(2, "Bob", "Barker", "bbk", "priceRight", "host@priceisright.com"));

    }

    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }

    @Test(expected = RuntimeException.class)
    public void authenticateWithInvalidCredentials() {

        sut.authenticate("", "");

    }

    @Test
    public void authenticateWithUnknownCredentials() {
        UserInfo expectedUser = new UserInfo("Adam", "Inn", "admin", "p4ssw0rd", "admin@revbooks.com");
        Mockito.when(mockUserRepo.findUserByCredentials(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(java.util.Optional.of(expectedUser));

        UserInfo actualResult = sut.authenticate("admin", "password");

        Assert.assertEquals(expectedUser, actualResult);
    }

    @Test
    public void authenticateWithValidCredentials() {
        UserInfo expectedUser = new UserInfo("Adam", "Inn", "admin", "p4ssw0rd", "admin@revbooks.com");
        Mockito.when(mockUserRepo.findUserByCredentials(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(java.util.Optional.of(expectedUser));

        UserInfo actualResult = sut.authenticate("admin", "p4ssw0rd");

        Assert.assertEquals(expectedUser, actualResult);

    }
}

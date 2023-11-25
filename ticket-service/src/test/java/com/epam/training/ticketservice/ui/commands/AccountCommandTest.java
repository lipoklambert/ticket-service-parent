package com.epam.training.ticketservice.ui.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AccountCommandTest {


    AccountCommand accountCommand = new AccountCommand();


    @Test
    void describeAccount() {
        assertEquals("You are not signed in", accountCommand.describeAccount());
        accountCommand.signInAdmin("admin", "admin");
        assertEquals("Signed in with privileged account 'admin'", accountCommand.describeAccount());
    }
}
package com.epam.training.ticketservice.ui.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountCommandTest {

    AccountCommand accountCommand = new AccountCommand();

    @Test
    void signInAdmin_ValidCredentials_Success() {
        assertEquals("You signed in successfully", accountCommand.signInAdmin("admin", "admin"));
        assertTrue(accountCommand.isAuthentication());
    }

    @Test
    void signInAdmin_InvalidCredentials_Failure() {
        assertEquals("Login failed due to incorrect credentials", accountCommand.signInAdmin("invalid", "invalid"));
        assertFalse(accountCommand.isAuthentication());
    }

    @Test
    void signOut_SignedIn_Success() {
        accountCommand.signInAdmin("admin", "admin");
        assertEquals("You signed out successfully", accountCommand.signOut());
        assertFalse(accountCommand.isAuthentication());
    }

    @Test
    void describeAccount_NotSignedIn() {
        assertEquals("You are not signed in", accountCommand.describeAccount());
    }

    @Test
    void describeAccount_SignedIn() {
        accountCommand.signInAdmin("admin", "admin");
        assertEquals("Signed in with privileged account 'admin'", accountCommand.describeAccount());
    }
}

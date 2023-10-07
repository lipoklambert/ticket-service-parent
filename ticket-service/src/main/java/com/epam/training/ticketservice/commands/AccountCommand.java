package com.epam.training.ticketservice.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;


@ShellComponent
public class AccountCommand extends SecurityConfiguration {

    @ShellMethod(key = "sign in privileged", value = "Sign in as admin.")
    public String signInAdmin(String username, String pass) {
        if (username.equals("admin") && pass.equals("admin")) {
            setAuthentication(true);
            return "You signed in successfully";
        } else {
            setAuthentication(false);
            return "Login failed due to incorrect credentials";
        }
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "sign out", value = "Sign out.")
    public String signOut() {
        setAuthentication(false);
        return "You signed out successfully";
    }

    @ShellMethod(key = "describe account", value = "Describes the account.")
    public String describeAccount() {
        if (isAuthentication()) {
            return "Signed in with privileged account 'admin'";
        } else {
            return "You are not signed in";
        }
    }

}

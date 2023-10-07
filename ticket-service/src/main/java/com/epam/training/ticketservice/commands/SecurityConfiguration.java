package com.epam.training.ticketservice.commands;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Primary;
import org.springframework.shell.Availability;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SecurityConfiguration implements PromptProvider {
    private static boolean authentication = false;

    public static void setAuthentication(boolean authentication) {
        SecurityConfiguration.authentication = authentication;
    }

    public static boolean isAuthentication() {
        return authentication;
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("Ticket service>");
    }

    public static Availability isAdmin() {
        if (authentication) {
            return Availability.available();
        }
        return Availability.unavailable("You are not signed in");
    }
}

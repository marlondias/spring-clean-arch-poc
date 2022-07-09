package com.marlon.CleanArchTeste.Application.UseCases.User.UpdateUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseInputBoundary;

import java.util.Map;

public final class InputBoundary implements UseCaseInputBoundary {
    private final int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public InputBoundary(int userId, Map<String, String> changedAttributes) throws Exception {
        this.userId = userId;
        boolean someValidKeyWasPassed = false;

        if (changedAttributes.containsKey("firstName")) {
            this.firstName = changedAttributes.get("firstName");
            someValidKeyWasPassed = true;
        }

        if (changedAttributes.containsKey("lastName")) {
            this.lastName = changedAttributes.get("lastName");
            someValidKeyWasPassed = true;
        }

        if (changedAttributes.containsKey("emailAddress")) {
            this.emailAddress = changedAttributes.get("emailAddress");
            someValidKeyWasPassed = true;
        }

        if (changedAttributes.containsKey("password")) {
            this.password = changedAttributes.get("password");
            someValidKeyWasPassed = true;
        }

        if (!someValidKeyWasPassed) {
            throw new Exception("None of the attribute keys are relevant for updating a User.");
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}

package com.marlon.CleanArchTeste.Application.UseCases.User.CreateUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseInputBoundary;

public final class CreateUserInputBoundary implements UseCaseInputBoundary {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String password;

    public CreateUserInputBoundary(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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

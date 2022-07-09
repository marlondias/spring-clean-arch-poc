package com.marlon.CleanArchTeste.Domain.Entities;

import com.marlon.CleanArchTeste.Domain.Contracts.Entity;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Services.StringHashingService;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;
import com.marlon.CleanArchTeste.Domain.Exceptions.EntityNotFoundException;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;
import com.marlon.CleanArchTeste.Domain.ValueObjects.PersonName;

import java.time.LocalDateTime;
import java.util.Map;

public class User extends Entity {
    protected PersonName name;
    protected EmailAddress email;
    protected String hashedPassword;
    protected LocalDateTime emailVerifiedAt;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    @Override
    public Map<String, Object> toMap() {
        return null;
        //TODO
    }

    public PersonName getName() {
        return name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public LocalDateTime getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setPersonName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void setEmailAddress(String emailAddress) {
        this.email = new EmailAddress(emailAddress);
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setHashedPasswordFromPlainText(StringHashingService stringHashingService, String plainTextPassword) throws DomainException {
        this.validatePasswordContent(plainTextPassword);
        this.hashedPassword = stringHashingService.getPasswordHash(plainTextPassword);
    }

    public void setEmailVerifiedAt(String dateTime) {
        this.emailVerifiedAt = LocalDateTime.parse(dateTime);
    }

    public void setCreatedAt(String dateTime) {
        this.createdAt = LocalDateTime.parse(dateTime);
    }

    public void setUpdatedAt(String dateTime) {
        this.updatedAt = LocalDateTime.parse(dateTime);
    }

    private void validatePasswordContent(String password) throws DomainException {
        if (password.length() < 5 || password.length() > 30) {
            throw new DomainException("A senha de usuário deve conter entre 5 e 30 caracteres.");
        }
        if (password.replaceAll("\\s", "").length() != password.length()) {
            throw new DomainException("A senha de usuário não pode conter espaços.");
        }

        String regexNumber = "(.*\\d.*)";
        if (!password.matches(regexNumber)) {
            throw new DomainException("A senha de usuário deve conter algum número.");
        }

        String regexLowerCaseLetter = "(.*[a-z].*)";
        if (!password.matches(regexLowerCaseLetter)) {
            throw new DomainException("A senha de usuário deve conter alguma letra minúscula.");
        }

        String regexUpperCaseLetter = "(.*[A-Z].*)";
        if (!password.matches(regexUpperCaseLetter)) {
            throw new DomainException("A senha de usuário deve conter alguma letra maiúscula.");
        }

        String regexValidSymbol = "(.*[!@#$%&*+\\-_=~^?].*)";
        if (!password.matches(regexValidSymbol)) {
            throw new DomainException("A senha de usuário deve conter algum símbolo válido (! @ # $ % & * + - _ = ~ ^ ?).");
        }

        String regexAllValidChars = ".*([!@#$%&*+\\-_=~^?\\w]).*";
        if (!password.replaceAll(regexAllValidChars, "").isEmpty()) {
            throw new DomainException("A senha de usuário só pode conter símbolos válidos (! @ # $ % & * + - _ = ~ ^ ?) e nenhum outro.");
        }
    }

    public boolean isPasswordCorrect(StringHashingService stringHashingService, String password) throws Exception {
        if (this.hashedPassword == null) {
            throw new Exception("Não há como comparar a senha com uma hash indefinida.");
        }
        return stringHashingService.checkPasswordHashMatches(password, this.hashedPassword);
    }

    public boolean isEmailAddressAlreadyInUse(UserQueriesRepository repository, EmailAddress emailAddress) {
        try {
            repository.findByEmail(emailAddress);
            return true;
        } catch (EntityNotFoundException notFoundEx) {
            return false;
        }
    }

}

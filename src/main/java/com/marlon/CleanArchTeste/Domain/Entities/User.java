package com.marlon.CleanArchTeste.Domain.Entities;

import com.marlon.CleanArchTeste.Domain.Contracts.Entity;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Services.StringHashingService;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;
import com.marlon.CleanArchTeste.Domain.Exceptions.EntityNotFoundException;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;
import com.marlon.CleanArchTeste.Domain.ValueObjects.PersonName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class User extends Entity {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected PersonName name;
    protected EmailAddress email;
    protected String hashedPassword;
    protected LocalDateTime emailVerifiedAt;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    @Override
    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("id", this.id);
        map.put("name", (this.name != null) ? this.name.toMap() : null);
        map.put("email", (this.email != null) ? this.email.toMap() : null);
        map.put("hashedPassword", this.hashedPassword);

        map.put("emailVerifiedAt", (this.emailVerifiedAt != null) ? dateTimeFormatter.format(this.emailVerifiedAt) : null);
        map.put("createdAt", (this.createdAt != null) ? dateTimeFormatter.format(this.createdAt) : null);
        map.put("updatedAt", (this.updatedAt != null) ? dateTimeFormatter.format(this.updatedAt) : null);

        return map;
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

    public User setPersonName(String firstName, String lastName) {
        this.name = (lastName.isEmpty()) ? new PersonName(firstName) : new PersonName(firstName, lastName);
        return this;
    }

    public User setEmailAddress(String emailAddress) {
        this.email = new EmailAddress(emailAddress);
        return this;
    }

    public User setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    public User setHashedPasswordFromPlainText(StringHashingService stringHashingService, String plainTextPassword) throws DomainException {
        this.validatePasswordContent(plainTextPassword);
        this.hashedPassword = stringHashingService.getPasswordHash(plainTextPassword);
        return this;
    }

    public User setEmailVerifiedAt(String dateTime) {
        this.emailVerifiedAt = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return this;
    }

    public User setCreatedAt(String dateTime) {
        this.createdAt = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return this;
    }

    public User setUpdatedAt(String dateTime) {
        this.updatedAt = LocalDateTime.parse(dateTime, dateTimeFormatter);
        return this;
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
        var user = repository.findByEmail(emailAddress);
        return user != null;
    }

}

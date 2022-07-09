package com.marlon.CleanArchTeste.Domain.ValueObjects;

import com.marlon.CleanArchTeste.Domain.Contracts.ValueObject;

import java.util.HashMap;
import java.util.Map;

public class PersonName implements ValueObject {
    protected String firstName;
    protected String lastName;
    protected String fullName;

    public PersonName(String firstName)
    {
        if (firstName.isBlank()) {
            throw new IllegalArgumentException("Primeiro nome não pode ser string vazia.");
        }
        this.firstName = firstName.trim();
        this.lastName = "";
        this.fullName = this.firstName;
    }

    public PersonName(String firstName, String lastName) throws IllegalArgumentException
    {
        if (firstName.isBlank()) {
            throw new IllegalArgumentException("Primeiro nome não pode ser string vazia.");
        }
        if (firstName.trim().length() > 40) {
            throw new IllegalArgumentException("Primeiro nome não pode ter mais de 40 caracteres.");
        }
        if (lastName.isBlank()) {
            throw new IllegalArgumentException("Sobrenome não pode ser string vazia.");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.fullName = String.format("%s %s", this.firstName, this.lastName);
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getFullName()
    {
        return this.fullName;
    }

    @Override
    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("firstName", this.firstName);
        map.put("lastName", this.lastName);
        map.put("fullName", this.fullName);
        return map;
    }
}

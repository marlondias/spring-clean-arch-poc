package com.marlon.CleanArchTeste.Domain.Contracts;

import java.util.Map;

public abstract class Entity {

    protected int id;

    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("ID da entidade não pode ser nulo ou negativo.");
        }
        this.id = value;
    }

    public abstract Map<String, Object> toMap();

}

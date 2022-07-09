package com.marlon.CleanArchTeste.Application.UseCases.User.CreateUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseOutputBoundary;

import java.util.Map;

public final class OutputBoundary implements UseCaseOutputBoundary {
    private final String message;

    public OutputBoundary(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }
}

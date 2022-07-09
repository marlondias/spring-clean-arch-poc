package com.marlon.CleanArchTeste.Application.UseCases.User.GetUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseOutputBoundary;
import com.marlon.CleanArchTeste.Domain.Entities.User;

import java.util.HashMap;
import java.util.Map;

public final class OutputBoundary implements UseCaseOutputBoundary {
    private final String message;
    private User user;

    public OutputBoundary(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("user", this.user.toMap());
        return map;
    }
}

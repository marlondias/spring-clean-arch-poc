package com.marlon.CleanArchTeste.Application.UseCases.User.GetAllUsers;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseOutputBoundary;
import com.marlon.CleanArchTeste.Domain.Entities.User;

import java.util.*;

public final class OutputBoundary implements UseCaseOutputBoundary {
    private final String message;
    private final List<User> users;

    public OutputBoundary(String message) {
        this.message = message;
        users = new ArrayList<>();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        var usersAsMaps = users.stream().map(User::toMap).toList();
        map.put("users", usersAsMaps);
        return map;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}

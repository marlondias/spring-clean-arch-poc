package com.marlon.CleanArchTeste.Application.UseCases.User.GetUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseInputBoundary;

public final class InputBoundary implements UseCaseInputBoundary {
    private int userId;

    public InputBoundary(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}

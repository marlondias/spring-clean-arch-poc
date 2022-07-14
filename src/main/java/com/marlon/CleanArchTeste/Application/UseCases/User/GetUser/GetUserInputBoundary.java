package com.marlon.CleanArchTeste.Application.UseCases.User.GetUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseInputBoundary;

public final class GetUserInputBoundary implements UseCaseInputBoundary {
    private final int userId;

    public GetUserInputBoundary(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}

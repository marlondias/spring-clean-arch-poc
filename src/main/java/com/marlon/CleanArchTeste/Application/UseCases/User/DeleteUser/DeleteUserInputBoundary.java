package com.marlon.CleanArchTeste.Application.UseCases.User.DeleteUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseInputBoundary;

public final class DeleteUserInputBoundary implements UseCaseInputBoundary {
    private final int userId;

    public DeleteUserInputBoundary(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

}

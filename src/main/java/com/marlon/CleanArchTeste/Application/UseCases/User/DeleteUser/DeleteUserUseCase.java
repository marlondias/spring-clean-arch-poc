package com.marlon.CleanArchTeste.Application.UseCases.User.DeleteUser;

import com.marlon.CleanArchTeste.Application.Exceptions.ApplicationException;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;

public class DeleteUserUseCase {
    private final UserCommandsRepository userCommandsRepository;

    public DeleteUserUseCase(UserCommandsRepository userCommandsRepository) {
        this.userCommandsRepository = userCommandsRepository;
    }

    public OutputBoundary handle(InputBoundary input) throws DomainException, ApplicationException {
        this.userCommandsRepository.deleteById(input.getUserId());
        return new OutputBoundary("Usuário " + input.getUserId() + " foi deletado!");
    }
}

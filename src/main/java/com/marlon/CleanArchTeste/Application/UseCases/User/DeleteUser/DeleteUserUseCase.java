package com.marlon.CleanArchTeste.Application.UseCases.User.DeleteUser;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserUseCase {
    private final UserCommandsRepository userCommandsRepository;

    public DeleteUserUseCase(UserCommandsRepository userCommandsRepository) {
        this.userCommandsRepository = userCommandsRepository;
    }

    public OutputBoundary handle(InputBoundary input) {
        this.userCommandsRepository.deleteById(input.getUserId());
        return new OutputBoundary("Usuário " + input.getUserId() + " foi deletado!");
    }
}

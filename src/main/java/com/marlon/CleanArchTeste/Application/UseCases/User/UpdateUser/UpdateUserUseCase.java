package com.marlon.CleanArchTeste.Application.UseCases.User.UpdateUser;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Services.StringHashingService;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;

public class UpdateUserUseCase {
    private final UserCommandsRepository userCommandsRepository;
    private final UserQueriesRepository userQueriesRepository;
    private final StringHashingService stringHashingService;

    public UpdateUserUseCase(
        UserCommandsRepository userCommandsRepository,
        UserQueriesRepository userQueriesRepository,
        StringHashingService stringHashingService
    ) {
        this.userCommandsRepository = userCommandsRepository;
        this.userQueriesRepository = userQueriesRepository;
        this.stringHashingService = stringHashingService;
    }

    public OutputBoundary handle(InputBoundary input) throws DomainException {
        var user = this.userQueriesRepository.findById(input.getUserId());
        this.replaceUserAttributes(input, user);
        this.userCommandsRepository.update(user);
        return new OutputBoundary("Usuário " + input.getUserId() + " foi atualizado!");
    }

    private void replaceUserAttributes(InputBoundary input, User user) throws DomainException {
        if (input.getEmailAddress() != null) {
            user.setEmailAddress(input.getEmailAddress());
        }

        if (input.getPassword() != null) {
            user.setHashedPasswordFromPlainText(this.stringHashingService, input.getPassword());
        }

        var newFirstName = input.getFirstName();
        var newLastName = input.getLastName();
        if (newFirstName != null || newLastName != null) {
            var oldName = user.getName();
            user.setPersonName(
                (newFirstName != null) ? newFirstName : oldName.getFirstName(),
                (newLastName != null) ? newLastName : oldName.getLastName()
            );
        }
    }
}

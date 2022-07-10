package com.marlon.CleanArchTeste.Application.UseCases.User.CreateUser;

import com.marlon.CleanArchTeste.Application.Contracts.UseCaseInteractor;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Services.StringHashingService;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;
import org.springframework.stereotype.Component;

@Component
public final class CreateUserUseCase implements UseCaseInteractor {
    private final UserCommandsRepository userCommandsRepository;
    private final UserQueriesRepository userQueriesRepository;
    private final StringHashingService stringHashingService;

    public CreateUserUseCase(
            UserCommandsRepository userCommandsRepository,
            UserQueriesRepository userQueriesRepository,
            StringHashingService stringHashingService
    ) {
        this.userCommandsRepository = userCommandsRepository;
        this.userQueriesRepository = userQueriesRepository;
        this.stringHashingService = stringHashingService;
    }

    public OutputBoundary handle(InputBoundary input) throws DomainException {
        var user = (new User())
            .setPersonName(input.getFirstName(), input.getLastName())
            .setEmailAddress(input.getEmailAddress())
            .setHashedPasswordFromPlainText(this.stringHashingService, input.getPassword());

        var isEmailInUse = user.isEmailAddressAlreadyInUse(this.userQueriesRepository, user.getEmail());
        if (isEmailInUse) {
            throw new DomainException("User with this email address already exists.");
        }

        this.userCommandsRepository.insert(user);
        return new OutputBoundary("Usuário criado!");
    }
}

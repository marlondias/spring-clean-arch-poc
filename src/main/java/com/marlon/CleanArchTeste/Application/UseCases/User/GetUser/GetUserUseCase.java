package com.marlon.CleanArchTeste.Application.UseCases.User.GetUser;

import com.marlon.CleanArchTeste.Application.Exceptions.ApplicationException;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;

public class GetUserUseCase {
    private final UserQueriesRepository userQueriesRepository;

    public GetUserUseCase(UserQueriesRepository userQueriesRepository) {
        this.userQueriesRepository = userQueriesRepository;
    }

    public OutputBoundary handle(InputBoundary input) throws DomainException, ApplicationException {
        var user = this.userQueriesRepository.findById(input.getUserId());
        var output = new OutputBoundary("Usuário encontrado!");
        output.setUser(user);
        return output;
    }
}

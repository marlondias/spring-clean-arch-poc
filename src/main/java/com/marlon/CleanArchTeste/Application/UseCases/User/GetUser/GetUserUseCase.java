package com.marlon.CleanArchTeste.Application.UseCases.User.GetUser;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import org.springframework.stereotype.Component;

@Component
public class GetUserUseCase {
    private final UserQueriesRepository userQueriesRepository;

    public GetUserUseCase(UserQueriesRepository userQueriesRepository) {
        this.userQueriesRepository = userQueriesRepository;
    }

    public OutputBoundary handle(GetUserInputBoundary input) {
        var user = this.userQueriesRepository.findById(input.getUserId());
        var output = new OutputBoundary("Usuário encontrado!");
        output.setUser(user);
        return output;
    }
}

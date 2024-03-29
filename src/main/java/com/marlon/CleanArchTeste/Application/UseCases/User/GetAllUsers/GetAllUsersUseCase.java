package com.marlon.CleanArchTeste.Application.UseCases.User.GetAllUsers;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersUseCase {
    private final UserQueriesRepository userQueriesRepository;

    public GetAllUsersUseCase(UserQueriesRepository userQueriesRepository) {
        this.userQueriesRepository = userQueriesRepository;
    }

    public OutputBoundary handle(GetAllUsersInputBoundary input) {
        var users = this.userQueriesRepository.getAll();
        if (users.isEmpty()) {
            return new OutputBoundary("Nenhum usuário encontrado!");
        }
        var output = new OutputBoundary(users.size() + " usuários encontrados!");
        for(User user : users) {
            output.addUser(user);
        }
        return output;
    }
}

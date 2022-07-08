package com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.CommandsRepository;
import com.marlon.CleanArchTeste.Domain.Entities.User;

public interface UserCommandsRepository extends CommandsRepository {

    void insert(User user);

    void update(User user);

    void deleteById(int id);

}

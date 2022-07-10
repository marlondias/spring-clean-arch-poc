package com.marlon.CleanArchTeste.Adapters.Repositories;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserRepositoryImpl implements UserCommandsRepository, UserQueriesRepository {
    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByEmail(EmailAddress emailAddress) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}

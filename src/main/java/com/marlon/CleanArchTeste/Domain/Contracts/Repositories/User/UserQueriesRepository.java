package com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User;

import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;

import java.util.Collection;

public interface UserQueriesRepository {

    User findById(int id);

    User findByEmail(EmailAddress emailAddress);

    Collection<User> getAll();

}

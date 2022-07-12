package com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User;

import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;
import java.util.List;

public interface UserQueriesRepository {

    User findById(int id);

    User findByEmail(EmailAddress emailAddress);

    List<User> getAll();

}

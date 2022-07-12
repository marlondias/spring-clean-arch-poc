package com.marlon.CleanArchTeste.Adapters.Repositories;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class UserRepositoryImpl implements UserCommandsRepository, UserQueriesRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
    public List<User> getAll() {
        String sql = "SELECT * FROm users";
        var rows = this.jdbcTemplate.queryForList(sql);
        var users = new ArrayList<User>();
        for (var row : rows) {
            users.add(this.getUserFromRow(row));
        }
        return users;
    }

    private User getUserFromRow(Map<String, Object> userRow) {
        userRow.values().removeIf(Objects::isNull);

        var user = new User();
        user.setId((long) userRow.get("id"));
        user
            .setPersonName((String) userRow.get("name"), "")
            .setEmailAddress((String) userRow.get("email"))
            .setHashedPassword((String) userRow.get("password"));

        if (userRow.containsKey("email_verified_at")) {
            user.setEmailVerifiedAt((String) userRow.get("email_verified_at"));
        }
        if (userRow.containsKey("created_at")) {
            user.setCreatedAt((String) userRow.get("created_at"));
        }
        if (userRow.containsKey("updated_at")) {
            user.setUpdatedAt((String) userRow.get("updated_at"));
        }

        return user;
    }
}

package com.marlon.CleanArchTeste.Adapters.Repositories;

import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserCommandsRepository;
import com.marlon.CleanArchTeste.Domain.Contracts.Repositories.User.UserQueriesRepository;
import com.marlon.CleanArchTeste.Domain.Entities.User;
import com.marlon.CleanArchTeste.Domain.Exceptions.EntityNotFoundException;
import com.marlon.CleanArchTeste.Domain.ValueObjects.EmailAddress;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class UserRepositoryImpl implements UserCommandsRepository, UserQueriesRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(User user) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var name = user.getName().getFirstName();
        var email = user.getEmail().getFullAddress();
        var password = user.getHashedPassword();
        var emailVerifiedAt = user.getEmailVerifiedAt() != null
                ? user.getEmailVerifiedAt().format(dateTimeFormatter) : null;

        String sql = "INSERT INTO " +
                "users (name, email, password, email_verified_at) " +
                "VALUES (?, ?, ?, ?);";

        this.jdbcTemplate.update(sql, name, email, password, emailVerifiedAt);
    }

    @Override
    public void update(User user) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var name = user.getName().getFirstName();
        var email = user.getEmail().getFullAddress();
        var password = user.getHashedPassword();
        var emailVerifiedAt = user.getEmailVerifiedAt() != null
                ? user.getEmailVerifiedAt().format(dateTimeFormatter) : null;

        String sql = "UPDATE users SET " +
                "name = ?, " +
                "email = ?, " +
                "password = ?, " +
                "email_verified_at = ? " +
                "WHERE id = ?";

        this.jdbcTemplate.update(sql, name, email, password, emailVerifiedAt, user.getId());
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        var args = new Object[] {id};
        this.jdbcTemplate.update(sql, args);
    }

    @Override
    public User findById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        var rows = this.jdbcTemplate.queryForList(sql, id);
        return rows.isEmpty() ? null : this.getUserFromRow(rows.get(0));
    }

    @Override
    public User findByEmail(EmailAddress emailAddress) {
        String sql = "SELECT * FROM users WHERE email = ?";
        var rows = this.jdbcTemplate.queryForList(sql, emailAddress.getFullAddress());
        return rows.isEmpty() ? null : this.getUserFromRow(rows.get(0));
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
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

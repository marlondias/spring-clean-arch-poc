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
        var values = new HashMap<String, Object>();
        values.put("name", user.getName().getFirstName());
        values.put("email", user.getEmail().getFullAddress());
        values.put("password", user.getHashedPassword());
        values.put("email_verified_at", user.getEmailVerifiedAt().format(dateTimeFormatter));

        //TODO
        String sql = "INSERT INTO users (...) VALUES ()";
        this.jdbcTemplate.update(sql, values);
    }

    @Override
    public void update(User user) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var values = new HashMap<String, Object>();
        values.put("name", user.getName().getFirstName());
        values.put("email", user.getEmail().getFullAddress());
        values.put("password", user.getHashedPassword());
        values.put("email_verified_at", user.getEmailVerifiedAt().format(dateTimeFormatter));

        //TODO
        String sql = "UPDATE users (...)";
        this.jdbcTemplate.update(sql, values);
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
        var row = this.jdbcTemplate.queryForMap(sql, id);
        return this.getUserFromRow(row);
    }

    @Override
    public User findByEmail(EmailAddress emailAddress) {
        String sql = "SELECT * FROM users WHERE email = ?";
        var row = this.jdbcTemplate.queryForMap(sql, emailAddress.getFullAddress());
        return this.getUserFromRow(row);
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

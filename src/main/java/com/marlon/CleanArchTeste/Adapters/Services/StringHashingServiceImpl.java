package com.marlon.CleanArchTeste.Adapters.Services;

import com.marlon.CleanArchTeste.Domain.Contracts.Services.StringHashingService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class StringHashingServiceImpl implements StringHashingService {
    @Override
    public String getPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPasswordHashMatches(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

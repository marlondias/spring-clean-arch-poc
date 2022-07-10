package com.marlon.CleanArchTeste.Adapters.Services;

import com.marlon.CleanArchTeste.Domain.Contracts.Services.StringHashingService;
import org.springframework.stereotype.Component;

@Component
public class StringHashingServiceImpl implements StringHashingService {
    @Override
    public String getPasswordHash(String password) {
        return null;
        //TODO
    }

    @Override
    public boolean checkPasswordHashMatches(String password, String hashedPassword) {
        return false;
        //TODO
    }
}

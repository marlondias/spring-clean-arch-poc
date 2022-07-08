package com.marlon.CleanArchTeste.Domain.Contracts.Services;

public interface StringHashingService {

    String getPasswordHash(String password);

    boolean checkPasswordHashMatches(String password, String hashedPassword);

}

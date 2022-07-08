package com.marlon.CleanArchTeste.Application.Contracts;

import java.util.Map;

public interface UseCaseOutputBoundary {
    String getMessage();

    Map<String, Object> toMap();
}

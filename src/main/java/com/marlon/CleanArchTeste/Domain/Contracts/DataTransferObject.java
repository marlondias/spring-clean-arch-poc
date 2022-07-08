package com.marlon.CleanArchTeste.Domain.Contracts;

import java.util.Map;

public interface DataTransferObject {

    void fill(Map<String, Object> $fillData);

    Map<String, Object> toMap();
}

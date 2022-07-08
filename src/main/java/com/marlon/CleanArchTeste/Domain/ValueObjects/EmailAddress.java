package com.marlon.CleanArchTeste.Domain.ValueObjects;

import com.marlon.CleanArchTeste.Domain.Contracts.ValueObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddress implements ValueObject {
    protected String fullAddress;
    protected String localPart;
    protected String domainPart;

    public EmailAddress(String emailAddress) {
        emailAddress = emailAddress.trim();
        if (emailAddress.isEmpty()) {
            throw new IllegalArgumentException("Endereço de e-mail não pode ser string vazia.");
        }
        String patternValidEmail = "^[^\\.]([\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}\\~\\.\\w\\d]+)[^\\.]@([\\-\\.\\w\\d]+)$";
        Matcher matcher = Pattern.compile(patternValidEmail).matcher(emailAddress);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Endereço de e-mail não está em formato válido.");
        }
        this.localPart = matcher.group(1);
        this.domainPart = matcher.group(2);
        this.fullAddress = emailAddress;
    }

    public String getFullAddress()
    {
        return this.fullAddress;
    }

    public String getLocalPart()
    {
        return this.localPart;
    }

    public String getDomainPart()
    {
        return this.domainPart;
    }

    @Override
    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("fullAddress", this.fullAddress);
        map.put("localPart", this.localPart);
        map.put("domainPart", this.domainPart);
        return map;
    }

}

package com.accounts.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class AccountContactInfoDto{
    private String message;
    private Map<String, String> contactDetails;
    private List<String>onCallSupport;
}

package com.accounts.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDto(String message,
                                    Map<String, String> contactDetails,
                                    List<String>onCallSupport) { }

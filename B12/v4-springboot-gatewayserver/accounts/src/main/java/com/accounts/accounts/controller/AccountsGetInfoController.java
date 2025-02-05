package com.accounts.accounts.controller;

import com.accounts.accounts.dto.AccountContactInfoDto;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@Validated
@Tag(
        name = "Get Info REST API for Accounts microservice controller",
        description = "This API allows to get the build, Java, Maven version and contact information of the account"
)
public class AccountsGetInfoController {
    Logger logger = LoggerFactory.getLogger(AccountsGetInfoController.class);

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountContactInfoDto accountContactInfoDto;

    @Operation(
            summary = "Get build info REST API",
            description = "This API allows to get the build version of the application"
    )
    @Retry(name= "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(
            @RequestHeader(value = "hogiathang-correlation-id") String correlationId
    ) {
        logger.debug("Getting build info invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);

    }
    private ResponseEntity<String> getBuildInfoFallback(
            @RequestHeader(value = "hogiathang-correlation-id") String correlationId,
            Throwable throwable) {
        logger.debug("Getting build info fallback invoked, correlationId: {}", correlationId);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("0.9");
    }

    @Operation(
            summary = "Get Java version REST API",
            description = "This API allows to get the Java version of the application"
    )
    @RateLimiter(name="getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        logger.debug("Getting Java version invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("build.version"));
    }

    private ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Customize fallback response");
    }

    @Operation(
            summary = "Get Maven version REST API",
            description = "This API allows to get the Maven version of the application"
    )
    @GetMapping("/maven-version")
    public ResponseEntity<String> getMavenVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("MAVEN_HOME"));
    }

    @Operation(
            summary = "Get contact info REST API",
            description = "This API allows to get the contact information of the account"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountContactInfoDto);
    }
}

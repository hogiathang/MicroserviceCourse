package com.loans.loans.controller;

import com.loans.loans.dto.LoansInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "api", produces = "application/json")
@RestController
@Validated
@Tag(
        name = "Get loans information",
        description = "Get loans build info, Java version, and contact info"
)
public class LoansInfoController {
    @Value("${build.version}")
    private String version;

    @Autowired
    private LoansInfoDto loansInfoDto;

    @Autowired
    private Environment environment;

    @Operation(
            summary = "Get build info",
            description = "Get the build info of the loans microservice"
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(version);
    }

    @Operation(
            summary = "Get Java version",
            description = "Get the Java version of the loans microservice"
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get contact info",
            description = "Get the contact info of the loans microservice"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<LoansInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansInfoDto);
    }
}

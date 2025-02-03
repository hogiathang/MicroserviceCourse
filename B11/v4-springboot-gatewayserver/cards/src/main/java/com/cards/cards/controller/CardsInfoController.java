package com.cards.cards.controller;

import com.cards.cards.dto.CardsContactDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Validated
@Tag(
        name = "Get information about Cards microservice",
        description = "This API allows to get information about build, Java version and contact information"
)
public class CardsInfoController {
    @Autowired
    private CardsContactDto cardsContactDto;
    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    private Environment environment;

    @Operation(
            summary = "Get build information REST API",
            description = "This API allows to get the build information of the Cards microservice"
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> buildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Get Java version REST API",
            description = "This API allows to get the Java version of the Cards microservice"
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get contact information REST API",
            description = "This API allows to get the contact information of the Cards microservice"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactDto> contactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactDto);
    }
}

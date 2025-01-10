package com.loans.loans;

import com.loans.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = LoansContactInfoDto.class)
@SpringBootApplication
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Loans Application",
				version = "1.0",
				description = "This is a simple microservice application for loans",
				license = @io.swagger.v3.oas.annotations.info.License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0.html"
				)
		),
		servers = {
				@io.swagger.v3.oas.annotations.servers.Server(
						url = "http://localhost:8090",
						description = "Local Server"
				)
		},
		tags = {
				@io.swagger.v3.oas.annotations.tags.Tag(
						name = "Loans",
						description = "This section contains all the APIs related to loans"
				)
		},
		externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
				description = "For more information, click here",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}

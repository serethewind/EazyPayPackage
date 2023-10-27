package com.eazypay.accounts;

import com.eazypay.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

//the value of the auditorAwareRef matches the value inside the component annotation in the AuditAwareClass
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
//for reading configuration properties using @ConfigurationProperties annotation, we map the DTO that corresponds to the configuration in the application.properties
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservices REST API Documentation",
				description = "EazyPay Account Microservices REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Noah Uhunmwangho-Johnson",
						email = "serethewind@gmail.com",
						url = "https://github.com/serethewind"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/serethewind"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyPay Account Microservices REST API Documentation"
		)
)
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

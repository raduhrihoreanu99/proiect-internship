package com.kronsoft.internship.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .directModelSubstitute(LocalDate.class, String.class)
        .directModelSubstitute(LocalDateTime.class, String.class)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.kronsoft.internship.controllers"))
        .paths(PathSelectors.any())
        .build();
  }
}

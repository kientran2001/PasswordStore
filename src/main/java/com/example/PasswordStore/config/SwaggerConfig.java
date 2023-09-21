package com.example.PasswordStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    // tu Spring 3 Springfox khong con ho tro ma thay vao do la Springdoc
    @Bean
    public Docket createApiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rs.RetailStore.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
                .title("Retail Store Application")
                .description("Retail Store description")
                .version("1.0")
                .build();
    }
}

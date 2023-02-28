package com.akb.gestionstock.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                        .description("Documentation de l'API Gestion de Stock")
                        .title("API REST de Gestion de Stock")
                        .build()
                )
                .groupName("")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.akb.gestionstock"))
                .paths(PathSelectors.ant(APP_ROOT + "/**"))
                .build();
    }
}

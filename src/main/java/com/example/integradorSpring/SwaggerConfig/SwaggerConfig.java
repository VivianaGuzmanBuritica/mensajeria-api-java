package com.example.integradorSpring.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

public class SwaggerConfig {
    @Configuration
    @EnableSwagger2
    public class ConfiguracionSwagger {

           @Bean
      Docket api(){
         return new Docket(DocumentationType.SWAGGER_2).select()
                   .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any()).build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("API REST de reservas de habitación de hotel")
                    .description("Esta API REST te permite gestionar reservas de habitaciones en un hotel.")
                    .version("1.0.0")
                    .build();
        }
    }
}

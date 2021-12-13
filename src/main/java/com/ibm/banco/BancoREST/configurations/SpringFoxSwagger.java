package com.ibm.banco.BancoREST.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwagger {

    @Bean
    public Docket getDocket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ibm.banco.BancoREST.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Tarjetas Backend API",
                "API para el manejo de recomendacione spara tarjetas a corde a un perfil",
                "V1",
                "Terminos del servicio",
                new Contact("Luis Escobar", "www.google.com", "lescobarv97@gmail.com"),
                "Licencia de API", "API licencia url", Collections.emptyList()
        );
    }
}

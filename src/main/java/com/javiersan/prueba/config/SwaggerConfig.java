package com.javiersan.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
	/**
* Publish a bean to generate swagger2 endpoints
* @return a swagger configuration bean
*/
@Bean
public Docket usersApi() {
return new Docket(DocumentationType.SWAGGER_2)
.apiInfo(usersApiInfo())
.select()
.paths(userPaths())
.apis(RequestHandlerSelectors.any())
.build()
.useDefaultResponseMessages(false);
}

private ApiInfo usersApiInfo() {
return new ApiInfoBuilder()
.title("Service User")
.version("1.0")
.license("Apache License Version 2.0")
.build();
}

private Predicate&amp;amp;amp;amp;amp;amp;amp;amp;amp;lt;String&amp;amp;amp;amp;amp;amp;amp;amp;amp;gt; userPaths() {
return regex("/user.*");
}
}

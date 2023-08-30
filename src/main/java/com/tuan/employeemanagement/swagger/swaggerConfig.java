//package com.tuan.employeemanagement.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//@Configuration
//
//
//public class swaggerConfig {
//
//    @Bean
//    public Docket postsApi() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//
//    }
//
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo())
//                ; // đặt base URL ở đây
//    }
//
//    private ApiInfo apiInfo() {
//
//        return new ApiInfoBuilder().title("Course API")
//
//                .description("xadmin Course API Documentation Generateed Using SWAGGER2 for our Course Rest API")
//
//                .termsOfServiceUrl("https://www.youtube.com/channel/UC-85Tmx8lhNZQmBNbxZiqkw")
//
//                .license("Xadmin Rest API License")
//
//                .licenseUrl("https://www.youtube.com/channel/UC-85Tmx8lhNZQmBNbxZiqkw").version("1.0").build();
//
//    }
//
//
//}
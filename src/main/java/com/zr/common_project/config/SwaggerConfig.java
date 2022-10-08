package com.zr.common_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //swagger会帮助我们生成接口文档
    /*
    * 1，配置文档信息
    * 2，配置生成信息
    * 3，Docket封装接口文档信息
    *
    * */
    @Bean
    public Docket getDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("xx项目后端接口说明")
                .description("此文档详细说明了xx项目的后端接口规范...")
                .version("v 2.1");
        ApiInfo apiInfo = apiInfoBuilder.build();
        docket.apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.basePackage("com.zr.common_project.api"))
                .paths(PathSelectors.any())//.regex("/user/")
                .build();
        return docket;


    }


}


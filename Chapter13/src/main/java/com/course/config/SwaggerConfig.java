package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.PathAdjuster;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 86186
 * @date 2020/3/26 14:25
 * @Description
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select().paths(PathSelectors.regex("/.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("UserManger service API")
                .contact(new Contact("Pofe","","162188190@qq.com"))
                .description("this is userManger service API")
                .version("1.0")
                .build();
    }
}

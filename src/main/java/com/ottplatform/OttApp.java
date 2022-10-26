package com.ottplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OttApp {

    public static void main(String[] args) {
        SpringApplication.run(OttApp.class, args);
    }

    /*
     * @Bean public Docket productApi() { return new Docket(DocumentationType.SWAGGER_2).select()
     * .apis(RequestHandlerSelectors.basePackage("com.icbtcbt")).build(); }
     */

}

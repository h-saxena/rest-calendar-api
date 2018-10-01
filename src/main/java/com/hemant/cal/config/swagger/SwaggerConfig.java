package com.hemant.cal.config.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT = new Contact(
      "Hemant Saxena", "url", "java.hemant@gmail.com");
  
  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
      "AppCal API", "Description", "1.0",
      "urn:tos", DEFAULT_CONTACT, 
      "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
      new HashSet<String>(Arrays.asList("application/json",
          "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.hemant.cal.api"))
                //.paths(PathSelectors.ant("/*"))
                .build().apiInfo(apiInfo()) ;
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, Lists.<ResponseMessage>newArrayList(new ResponseMessageBuilder()
//                        .code(500).message("500 message").responseModel(new ModelRef("Error"))
//                        .build(), new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build()));
    }

    private ApiInfo apiInfo() {
        return DEFAULT_API_INFO;
    }

}
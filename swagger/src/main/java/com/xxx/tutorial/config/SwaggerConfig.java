package com.xxx.tutorial.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.xxx.tutorial"})
public class SwaggerConfig extends WebMvcConfigurationSupport{

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("对外开放接口API 文档")
				.description("HTTP对外开放接口")
				.version("1.0.0")
				.termsOfServiceUrl("http://xxx.xxx.com")
				.license("LICENSE")
				.licenseUrl("http://xxx.xxx.com")
				.build();
	}

	
/*	 @Bean
	    public Docket swaggerSpringMvcPlugin() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .groupName("business-api")
	                .select()   // 选择那些路径和api会生成document
	.apis(RequestHandlerSelectors.basePackage("com.custom.web"))
	                .paths(paths())
	                //.apis(RequestHandlerSelectors.any())  // 对所有api进行监控
	                //.paths(PathSelectors.any())   // 对所有路径进行监控
	.build()
	                .securitySchemes(securitySchemes())
	                .securityContexts(securityContexts());
	    }

	    private Predicate<String> paths() {
	        return or(regex("/person.*"));
	    }

	    private List<ApiKey> securitySchemes() {
	        return newArrayList(
	                new ApiKey("clientId", "客户端ID", "header"),
	                new ApiKey("clientSecret", "客户端秘钥", "header"),
	                new ApiKey("accessToken", "客户端访问标识", "header"));
	    }

	    private List<SecurityContext> securityContexts() {
	        return newArrayList(
	                SecurityContext.builder()
	                        .securityReferences(defaultAuth())
	                        .forPaths(PathSelectors.regex("/*.*"))
	                        .build()
	        );
	    }

	    List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return newArrayList(
	                new SecurityReference("clientId", authorizationScopes),
	                new SecurityReference("clientSecret", authorizationScopes),
	                new SecurityReference("accessToken", authorizationScopes));
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Spring 中使用Swagger2构建RESTful API")
	                .termsOfServiceUrl("http://blog.csdn.net/yangshijin1988")
	                .description("此API提供接口调用")
	                .license("License Version 2.0")
	                .licenseUrl("http://blog.csdn.net/yangshijin1988")
	                .version("2.0").build();
	    }*/
}
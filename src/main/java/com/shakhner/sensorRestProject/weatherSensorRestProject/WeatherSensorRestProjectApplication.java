package com.shakhner.sensorRestProject.weatherSensorRestProject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class WeatherSensorRestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherSensorRestProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public Docket swaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.shakhner.sensorRestProject.weatherSensorRestProject.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInformation());
	}

	private ApiInfo getApiInformation(){
		return new ApiInfo("Weather Sensor REST API",
				"This application is developed to receive, validate, process data received from weather sensors.",
				"1.0",
				"https://github.com/FrakBeerlover69",
				new Contact("Aliaksei Shakhner", "https://github.com/FrakBeerlover69", "shahner.lesha@gmail.com"),
				"API License",
				"https://github.com/FrakBeerlover69",
				Collections.emptyList()
		);
	}
}

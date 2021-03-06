package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.function.handler.EmployeeHandler;

@Configuration
public class EmployeeRoute {

	@Bean
	public RouterFunction<ServerResponse> empRoute(@Autowired EmployeeHandler employeeHandler) {
		return RouterFunctions.route()
				.GET("/emp/all", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::findAll)
				.GET("/emp/{empId}", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::findByEmpId)
				.POST("/emp/dto", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::findByDTO)
				.build();
	}

}

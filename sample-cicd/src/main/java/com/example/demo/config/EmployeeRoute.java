package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.handler.EmployeeHandler;

@Configuration
public class EmployeeRoute {

	@Bean
	public RouterFunction<ServerResponse> empRoute(@Autowired EmployeeHandler employeeHandler) {
		return RouterFunctions.route()
				.GET("/employee/find/all", RequestPredicates.accept(MediaType.APPLICATION_JSON),
						employeeHandler::findAll)
				.GET("/employee/find/by/empId/{empId}", RequestPredicates.accept(MediaType.APPLICATION_JSON),
						employeeHandler::findByEmpId)
				.POST("/employee/find/one/by/criteria/", RequestPredicates.accept(MediaType.APPLICATION_JSON),
						employeeHandler::findOneByCriteria)
				.POST("/employee/find/all/by/criteria/", RequestPredicates.accept(MediaType.APPLICATION_JSON),
						employeeHandler::findAllByCriteria)
				.POST("/employee/upsert", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::save)
				.POST("/employee/delete", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::delete)
				.POST("/employee/delete/all", RequestPredicates.accept(MediaType.APPLICATION_JSON),
						employeeHandler::deleteAll)
				.build();
	}

}

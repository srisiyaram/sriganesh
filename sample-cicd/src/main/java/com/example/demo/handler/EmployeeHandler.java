package com.example.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.mongo.doc.Employee;
import com.example.demo.mongo.helper.ExampleBuilder;
import com.example.demo.mongo.repo.EmployeeMongoRepository;

import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {

	@Autowired
	private EmployeeMongoRepository employeeMongoRepository;

	public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
				.body(employeeMongoRepository.findAll().map(this::toDTO), EmployeeDTO.class);
	}

	public Mono<ServerResponse> findByEmpId(ServerRequest serverRequest) {
		return Mono.justOrEmpty(serverRequest.pathVariable("empId")).map(Integer::parseInt)
				.flatMap(empId -> ServerResponse.ok().body(employeeMongoRepository.findByEmpId(empId).map(this::toDTO),
						EmployeeDTO.class));
	}

	public Mono<ServerResponse> findByCriteriaContainingAny(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(EmployeeDTO.class).map(this::toDoc).map(ExampleBuilder::containingAny).flatMap(
				ex -> ServerResponse.ok().body(employeeMongoRepository.findBy(ex).map(this::toDTO), EmployeeDTO.class));
	}

	public Mono<ServerResponse> findByCriteriaContainingAll(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(EmployeeDTO.class).map(this::toDoc).map(ExampleBuilder::containingAll).flatMap(
				ex -> ServerResponse.ok().body(employeeMongoRepository.findBy(ex).map(this::toDTO), EmployeeDTO.class));
	}

	public Mono<ServerResponse> save(ServerRequest serverRequest) {
//		return serverRequest.bodyToMono(EmployeeDTO.class).map(this::toDoc).map(employeeMongoRepository::save)
//				.flatMap(doc -> ServerResponse.ok().body(doc.map(this::toDTO), EmployeeDTO.class));
		return ServerResponse.ok().body(serverRequest.bodyToMono(EmployeeDTO.class).map(this::toDoc)
				.flatMap(employeeMongoRepository::save).map(this::toDTO), EmployeeDTO.class);
	}

	private EmployeeDTO toDTO(Employee empDoc) {
		return EmployeeDTO.builder().empId(empDoc.getEmpId()).empName(empDoc.getEmpName()).id(empDoc.getId()).build();
	}

	private Employee toDoc(EmployeeDTO empDTO) {
		return Employee.builder().empId(empDTO.getEmpId()).empName(empDTO.getEmpName()).id(empDTO.getId()).build();
	}

}

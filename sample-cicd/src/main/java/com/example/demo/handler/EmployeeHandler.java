package com.example.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.mongo.doc.Employee;
import com.example.demo.mongo.doc.Employee.EmployeeKey;
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
		return ServerResponse.ok().body(Mono.justOrEmpty(serverRequest.pathVariable("empId")).map(Integer::parseInt)
				.flatMap(employeeMongoRepository::findByEmployeeKeyEmpId).map(this::toDTO), EmployeeDTO.class);
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
		return ServerResponse.ok().body(serverRequest.bodyToMono(EmployeeDTO.class).map(this::toDoc)
				.flatMap(employeeMongoRepository::save).map(this::toDTO), EmployeeDTO.class);
	}

	public Mono<ServerResponse> delete(ServerRequest serverRequest) {
		return ServerResponse.ok().body(
				serverRequest.bodyToMono(EmployeeDTO.class).map(this::toDoc).flatMap(employeeMongoRepository::delete),
				Void.class);
	}

	private EmployeeDTO toDTO(Employee empDoc) {
		return EmployeeDTO.builder().empId(empDoc.getEmployeeKey().getEmpId())
				.empName(empDoc.getEmployeeKey().getEmpName()).build();
	}

	private Employee toDoc(EmployeeDTO empDTO) {
		return Employee.builder()
				.employeeKey(EmployeeKey.builder().empId(empDTO.getEmpId()).empName(empDTO.getEmpName()).build())
				.build();
	}

}

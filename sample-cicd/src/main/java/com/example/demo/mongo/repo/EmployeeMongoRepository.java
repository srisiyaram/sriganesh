package com.example.demo.mongo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.mongo.doc.Employee;
import com.example.demo.mongo.doc.EmployeeInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeMongoRepository extends ReactiveMongoRepository<Employee, String> {

	Mono<Employee> findByEmpInfoEmpId(Integer empId);

	Mono<Employee> findOneByEmpInfo(EmployeeInfo employeeInfo);
}

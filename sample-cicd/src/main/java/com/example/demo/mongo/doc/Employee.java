package com.example.demo.mongo.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Document
@NoArgsConstructor
public class Employee {

	@Id
	private String id;
	@Default
	private EmployeeInfo empInfo = EmployeeInfo.builder().build();

}

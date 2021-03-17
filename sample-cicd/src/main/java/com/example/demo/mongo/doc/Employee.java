package com.example.demo.mongo.doc;

import java.io.Serializable;

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
public class Employee implements Serializable {

	private static final long serialVersionUID = -4821713774265073276L;

	@Id
	private String id;
	@Default
	private EmployeeInfo empInfo = EmployeeInfo.builder().build();

}

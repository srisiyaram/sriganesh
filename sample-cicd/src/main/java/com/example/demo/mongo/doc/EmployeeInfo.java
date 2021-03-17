package com.example.demo.mongo.doc;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EmployeeInfo implements Serializable {
	private static final long serialVersionUID = -4890928347639197714L;
	private Integer empId;
	private String empName;
}

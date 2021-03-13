package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EmployeeDTO {
	private String id;
	private Integer empId;
	private String empName;

	@Override
	public String toString() {
		return "EmployeeDTO [" + (id != null ? "id=" + id + ", " : "") + (empId != null ? "empId=" + empId + ", " : "")
				+ (empName != null ? "empName=" + empName : "") + "]";
	}

}

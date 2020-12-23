package co.company.spring.dao;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Emp {
	@JsonProperty(value = "empId")
    String employeeId;   
	String firstName;
	String lastName;
	String email;	
//	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss") // 사용자 지정 형식으로 변환
	@JsonFormat(shape = Shape.STRING) //ISO-8601 형식으로 변환
	Date hireDate;	
	String jobId;	
	String departmentId;
	@JsonIgnore Integer salary;
	@JsonIgnore String msg;
}

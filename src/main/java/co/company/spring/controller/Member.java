package co.company.spring.controller;

import lombok.Builder;
import lombok.Data;

@Builder //객체 생성 (@NoArgsConstructor, @AllArgsConstructor)
@Data
public class Member {
	String id;
	String name;
	String pw;
	
}

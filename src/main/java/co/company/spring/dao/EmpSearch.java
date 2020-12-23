package co.company.spring.dao;

import lombok.Data;

// 검색 용도로 쓸 VO 파일
@Data
public class EmpSearch extends Emp {
	Integer minSalary;
	Integer maxSalary;
	Integer first;
	Integer last;
	String[] list; //여러 건 삭제용 리스트
}

package co.company.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpDAOJdbcTemplate {
	
	// EmpDAO(기존 방식)을 새로운 방식으로 변경해보는 실습
	// 쿼리만 기존 그대로 가지고 오기
	final String SELECT = "select * from employees";
	final String INSERT = "insert into employees("
			+ "EMPLOYEE_ID, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)"
			+ "values(?, ?, ?, SYSDATE, 'IT_PROG')";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 조회
	public List<Map<String, Object>> getListMap() {
		return jdbcTemplate.queryForList(SELECT);
	}
	
	// 등록
	public void insert(Emp emp) {
		Object[] param = new Object[]{
				emp.getEmployeeId(),
				emp.getLastName(),
				emp.getEmail()
		};
		jdbcTemplate.update(INSERT, param);
	}
	
	public List<Emp> getList() {
		return jdbcTemplate.query(SELECT, new RowMapper<Emp>() {

			@Override
			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp emp = new Emp();
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmployeeId(rs.getString("employee_id"));
				return emp;
			}});
	}
}

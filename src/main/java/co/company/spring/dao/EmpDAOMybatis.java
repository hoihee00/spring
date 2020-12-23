package co.company.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAOMybatis {

	@Autowired SqlSession sqlSession;
	
	// 조회
	public List<Emp> getEmpList() {
		return sqlSession.selectList(
				"EmpDAO.getEmpList"); //emp_mapper.xml의 namespace(EmpDAO)
		}
	}


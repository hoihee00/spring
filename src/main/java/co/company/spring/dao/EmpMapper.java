package co.company.spring.dao;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
	public List<Emp> getEmpList(EmpSearch emp);
	public Emp getEmp(Emp emp); //건수 조회 시 전체 조회와 같은 vo 넘겨야 함 (페이징할 때 건수에 맞는 카운트값을 가지고 오기 위해서)
	public int getCount(Emp emp);
	public List<Map<String, Object>> getStatDept();
	public int updateEmp(Emp emp);
	public int insertEmp(Emp emp);
	public void insertEmpProc(Emp emp);
	public int deleteEmp(Emp emp);
	public int deleteMultiEmp(EmpSearch emp); //다건 삭제는 리스트에 담아서 삭제
	public List<Jobs> jobSelect();
	public List<Dept> deptSelect();
}
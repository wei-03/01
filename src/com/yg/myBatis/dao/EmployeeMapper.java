package com.yg.myBatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yg.myBatis.pojo.Employee;

public interface EmployeeMapper {

	public Employee getEmpById(int id);
	
	public Employee getEmpByIdAndLastName(@Param("id")int id,@Param("lastName")String lastName);
	
	public  Employee getEmpByMap(Map<String, Object> map);
	
	public int addEmp(Employee employee);
	
	public void updateEmp(Employee employee);
	
	public void deleteEmp(Integer id);
	
}

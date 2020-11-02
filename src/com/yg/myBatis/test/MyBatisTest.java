package com.yg.myBatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.Alias;

//import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.yg.myBatis.dao.EmployeeMapper;
import com.yg.myBatis.pojo.Employee;
import org.junit.Test;

@Alias("emp")
public class MyBatisTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return  new SqlSessionFactoryBuilder().build(inputStream);
	}

	/*public SqlSession getSqlSession() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;

	}*/
	/*
	 * 第一种方式
	 */
	/*@Test
	public void test1() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			 Employee employee = session.selectOne("com.yg.myBatis.dao.EmployeeMapper.selectEmp",1);  //selectEmp
			System.out.println(employee);
		}finally{
			session.close();
		}
	}*/

	/*
	 * 第二种方式
	 */
	@Test
	public void test2() throws IOException{
		SqlSessionFactory sqlSessionFactory= getSqlSessionFactory();
		//不能自动提交
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapper employeeMapper= sqlSession.getMapper(EmployeeMapper.class);
			//查询
			//Employee employee=employeeMapper.getEmpById(1);

			Employee employee= employeeMapper.getEmpByIdAndLastName( 1, "lisi");
			//System.out.println(employee);

			//Map<String, Object> map=new HashMap<>();
			//map.put("id", 1);
			//map.put("lastname", "lisi");
			//Employee employee=employeeMapper.getEmpByMap(map);
			System.out.println(employee);

			//EmployeeMapper getEmpByIdAndLastName= sqlSession.getMapper(EmployeeMapper.class);
			//Employee employee=getEmpByIdAndLastName.getEmpById(1);
			//System.out.println(employee);

			//添加
			/*Employee employee=new Employee(null, "WWwu", "1@1", "1");
			int result=employeeMapper.addEmp(employee);
			System.out.println("result: "+result);
			System.out.println(employee);*/

			//更新
			/*Employee employee=new Employee(2, "Wwu", "2@2", "2");
			employeeMapper.updateEmp(employee);*/

			//删除
			//employeeMapper.deleteEmp(3);
			//提交数据
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
	}
}

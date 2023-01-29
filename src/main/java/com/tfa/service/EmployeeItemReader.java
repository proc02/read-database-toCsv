package com.tfa.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.tfa.entity.Employee;

@Component
public class EmployeeItemReader extends JdbcCursorItemReader<Employee> implements ItemReader<Employee> {

	public EmployeeItemReader(@Autowired DataSource dataSource) {
		setDataSource(dataSource);
		setSql("SELECT ID,NAME,SALARY FROM EMPLOYEE");
		setFetchSize(100);
		setRowMapper(new EmployeeRowMapper());
	}
	
	public class EmployeeRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee();
			emp.setId(rs.getInt("ID"));
			emp.setName(rs.getString("NAME"));
			emp.setSalary(rs.getDouble("SALARY"));
			return emp;
		}
		
	}
	
}

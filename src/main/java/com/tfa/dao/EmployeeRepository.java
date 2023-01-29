package com.tfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

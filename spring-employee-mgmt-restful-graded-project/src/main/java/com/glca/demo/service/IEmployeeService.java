package com.glca.demo.service;

import java.util.List;

import com.glca.demo.entity.Employee;
import com.glca.demo.entity.Role;
import com.glca.demo.entity.User;

public interface IEmployeeService {
	public List<Employee> getAllEmployees();

	public Employee addEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public boolean deleteById(int empId);

	public Employee getEmployeeById(int empId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstNameAsc();

	public List<Employee> sortByFirstNameDesc();

}

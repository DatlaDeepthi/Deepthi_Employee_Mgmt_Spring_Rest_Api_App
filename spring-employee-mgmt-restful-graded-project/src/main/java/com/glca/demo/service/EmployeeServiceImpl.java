package com.glca.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glca.demo.entity.Employee;
import com.glca.demo.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);

	}

	public Employee updateEmployee(Employee employee) {
		Employee emp = getEmployeeById(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		employeeRepository.save(emp);
		return emp;
	}

	@Override
	public boolean deleteById(int empId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(empId);
		return true;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Optional<Employee> result = employeeRepository.findById(empId);
		Employee emp = null;
		if (result.isPresent()) {
			emp = result.get();
		} else {
			throw new RuntimeException("Did not get Employee" + empId);
		}
		return emp;

	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public List<Employee> sortByFirstNameDesc() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByOrderByFirstNameDesc();
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

}

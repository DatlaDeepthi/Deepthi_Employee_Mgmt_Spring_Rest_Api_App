package com.glca.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glca.demo.entity.Employee;
import com.glca.demo.entity.Role;
import com.glca.demo.entity.User;
import com.glca.demo.service.IEmployeeService;
import com.glca.demo.service.IRoleService;
import com.glca.demo.service.IUserService;

@RestController
@RequestMapping("/emp")
public class EmployeeRestController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}

	// get all employees
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.getAllEmployees();
	}

	// GET/employee/{empId}
	@GetMapping("/employeesById/{empId}")
	public Employee getEmployeeById(@PathVariable("empId") int empId) {

		Employee emp = employeeService.getEmployeeById(empId);

		if (emp == null) {
			throw new RuntimeException("Employee id not found - " + empId);
		}
		return emp;
	}

	// add new employee
	@PostMapping("/addemployee")
	public Employee addEmployee(@RequestBody Employee employee) {

		employeeService.addEmployee(employee);

		return employee;
	}

	// update employee
	@PutMapping("/updateemployee")
	public Employee updateEmployee(@RequestBody Employee employees) {

		employeeService.updateEmployee(employees);

		return employees;
	}
	// delete employee

	@DeleteMapping("/deleteemployee/{empId}")
	public String deleteEmployee(@PathVariable("empId") int empId) {

		Employee employee = employeeService.getEmployeeById(empId);
		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + empId);
		}
		employeeService.deleteById(empId);
		return "Deleted Employee with id - " + empId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable("firstName") String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	@GetMapping("/employee-asc/sort/")
	public List<Employee> sortByFirstNameAsc() {
		return employeeService.sortByFirstNameAsc();
	}

	@GetMapping("/employee-desc/sort/")
	public List<Employee> sortByFirstNameDesc() {
		return employeeService.sortByFirstNameDesc();
	}

}

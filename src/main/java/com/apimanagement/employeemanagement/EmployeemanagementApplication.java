package com.apimanagement.employeemanagement;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apimanagement.employeemanagement.Entity.Account;
import com.apimanagement.employeemanagement.Entity.Department;
import com.apimanagement.employeemanagement.Entity.Employee;
import com.apimanagement.employeemanagement.Entity.Role;
import com.apimanagement.employeemanagement.Entity.RoleType;
import com.apimanagement.employeemanagement.Repository.AccountRepository;
import com.apimanagement.employeemanagement.Repository.DepartmentRepository;
import com.apimanagement.employeemanagement.Repository.EmployeeRepository;
import com.apimanagement.employeemanagement.Repository.RoleRepository;

@SpringBootApplication
public class EmployeemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository, AccountRepository accountRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository) {
		return args -> {
			Role user = new Role(RoleType.ROLE_USER);
			Role manager = new Role(RoleType.ROLE_ADMIN);
			Role admin = new Role(RoleType.ROLE_MANAGER);
			Account a  = new Account("quan", "1996");
			a.getRoles().add(user);

			Account b  = new Account("quan_Admin", "1996");
			b.getRoles().add(admin);

			Account c  = new Account("khanh", "1996");
			c.getRoles().add(manager);

			accountRepository.save(a);
			accountRepository.save(b);
			accountRepository.save(c);

			Employee khanh = new Employee("nguyen", "khanh", "khanhnguyen@gmail.com");

			// employeeRepository.save(khanh);
			// Optional<Role> role = roleRepository.findByName(RoleType.ROLE_MANAGER.name()); 
			// if(role.isPresent()) {
			// 	System.out.println(role.get());
			// } else {
			// 	System.out.println("not found");
			// }
			// System.out.println(employeeRepository.findByEmail("khanhnguyen@gmail.com").get());

			//Why Spring JPA Bidirectional OneToMany and ManyToOne is not updating the child side


			Department department = new Department("IT");
			// List<Employee> employees =  department.getEmployees();
			// employees.add(khanh);
			   khanh.setDepartment(department);
			  employeeRepository.save(khanh);
			//department.setEmployees(employees);
			 //departmentRepository.save(department);
			
			System.out.println(department);
			System.out.println(khanh);
			
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

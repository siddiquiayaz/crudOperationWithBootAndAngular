package com.crudwithspringbootandangular.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudwithspringbootandangular.repository.EmployeeRepository;
import com.crudwithspringbootandangular.entities.Employee;
import com.crudwithspringbootandangular.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/crud")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	@PostMapping("/saveEmployee")
	public Employee saveEmp(@RequestBody Employee employee) {
		return employeeRepository.save(employee);

	}
 
	@GetMapping("/getEmployee")
	public List<Employee> getAllEmp() {
		return (List<Employee>) employeeRepository.findAll();

	}
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") Long id) {
	    Optional<Employee> emp = employeeRepository.findById(id);

	    if (emp.isPresent()) {
	        return ResponseEntity.ok(emp.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PutMapping("/updateEmployees/{id}")
    
	public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);

		if (employeeOptional.isPresent()) {
			Employee existingEmployee = employeeOptional.get();
			existingEmployee.setFirstName(updatedEmployee.getFirstName());
			existingEmployee.setLastName(updatedEmployee.getLastName());
			existingEmployee.setEmailId(updatedEmployee.getEmailId());
			employeeRepository.save(existingEmployee);
			return ResponseEntity.ok(existingEmployee);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/deleteEmployees/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
		  Employee employee =employeeRepository.findById(id)
		  .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
		employeeRepository.delete(employee);
		 Map<String, Boolean> response  =new HashMap<>();
		  response.put("deleted", Boolean.TRUE);
		  return ResponseEntity.ok(response);
	}

}

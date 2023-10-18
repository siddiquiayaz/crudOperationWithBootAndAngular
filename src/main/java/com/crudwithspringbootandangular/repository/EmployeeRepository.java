package com.crudwithspringbootandangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudwithspringbootandangular.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

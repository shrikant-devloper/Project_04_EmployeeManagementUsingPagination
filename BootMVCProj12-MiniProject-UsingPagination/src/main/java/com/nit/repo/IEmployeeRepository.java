package com.nit.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.nit.model.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Integer>{

	public abstract Page<Employee> findAll(Pageable pageable);

}

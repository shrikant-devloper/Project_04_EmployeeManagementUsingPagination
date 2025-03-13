package com.nit.model;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="emp")
@Data

public class Employee implements Serializable{
	@Id
	@SequenceGenerator(name="gen1", sequenceName = "emp_id_sequence", initialValue = 101, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	
	private Integer empno;
	@Column(length = 20)
	
	private String ename;
	@Column(length = 20)
	
	private String job;
	
	private Float sal;
	
	private Integer deptno;
}

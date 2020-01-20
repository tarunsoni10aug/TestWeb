package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private long sal;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getSal() {
		return sal;
	}
	public Employee(String name, String address, long sal) {
		super();
		this.name = name;
		this.address = address;
		this.sal = sal;
	}
	public void setSal(long sal) {
		this.sal = sal;
	}
	public  Employee()
	{
		
	}
	
}

package employee;

import java.util.Date;

public class Employee {
	
	private String name;
	private int salary;
	private Date dateJoined;
	
	public Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
				
	}
	public String toString() {
		return name + " " + salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int s) {
		salary = s;
	}
	public Date getDateJoined() {
		return dateJoined;
	}
	public void setDateJoined(Date dj) {
		dateJoined = dj;
	}
	
		
	//public static void main(String[] args) {

	}

package student.javalang;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc;
		sc=new Scanner(System.in);
		String name;
		System.out.println("enter an employee name");
		name = sc.next();
		double salary;
		System.out.println("enter salary");
		salary = sc.nextInt();
		String fellow_name;
		System.out.println("enter a fellow employee name");
		fellow_name = sc.next();
		double friend_salary;
		System.out.println("enter a friend employee's salary");
		friend_salary = sc.nextInt();
		double total_salary;
		total_salary = salary + friend_salary;
		double salary_average;
		salary_average = total_salary/2;
		System.out.println("Hello " + name + " this is your salary " + salary);
		System.out.println("Hello, your friend employee's name is  " + fellow_name + " this is their salary " + friend_salary);
		System.out.println("Average salary is " + salary_average);
		System.out.println("Max salary " + Math.max(salary,  friend_salary));
		System.out.println("Min salary " + Math.min(salary,  friend_salary));
		StringBuilder sb1 = new StringBuilder("Yo ");
		sb1.append(name);
		sb1.append(fellow_name);
		sb1.insert(9, " imagine a tiger named ");
		String str1 = sb1.toString();
		System.out.println(sb1);
		
	}

}

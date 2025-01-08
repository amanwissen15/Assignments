package employee_application;

abstract class Employee {
	private String name;
	private int age;
	private int salary;
	private String designation;
	
	Employee(String name, int age, int salary, String designation) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.designation = designation;
	}
	
	protected void setage(int age) {
		if(age > 21)
			this.age = age;
		else
			System.out.println("Invalid Age");
	}
	
	protected void display() {
		System.out.println("Name: "+ name);
		System.out.println("Age: "+ age);
		System.out.println("Salary: "+ salary);
		System.out.println("Designation: "+ designation);
	}
	protected void raise_salary(int increment) {
		// process salary
		if(increment > 0)
			this.salary += increment;
		this.display();
	}
}

class Clerk extends Employee {
	Clerk(String name, int age, int salary) {
		super(name, age, salary, "Clerk");
	}
}

class Programmer extends Employee {
	Programmer(String name, int age, int salary) {
		super(name, age, salary, "Programmer");
	}
}

class Manager extends Employee {
	Manager(String name, int age, int salary) {
		super(name, age, salary, "Manager");
	}
	public void raise_salary_manager(Employee emp, int increment) {
		emp.raise_salary(increment);
	}
}

public class Assignment1 {
	public static void main(String[] args) {
		Programmer emp1 = new Programmer("Programmer1", 21, 25000);
		Manager emp2 = new Manager("Manager1", 34, 100000);
		emp1.display();
		System.out.println();
		emp2.display();
		System.out.println();
		emp2.raise_salary_manager(emp1, 5000);
	}
}

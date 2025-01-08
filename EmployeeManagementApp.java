import java.util.Scanner;

abstract class Employee {
	private String name;
	private int age;
	private int salary;
	private String designation;
	
	protected void setName (String name) {
		if (name.length() > 0)
			this.name = name;
		else
			System.out.println("Please enter a name then continue.");
	}
	
	protected void setAge (int age) {
		if (age > 20)
			this.age = age;
		else
			System.out.println("Please enter a valid age then continue.");;
	}
	
	protected void setSalary(int salary) {
		if (salary >= 20000)
			this.salary = salary;
		else
			System.out.println("Please enter a valid salary then continue.");
	}
	
	protected void setDesignation(String designation) {
		this.designation = designation;
	}
	
	protected void display() {
		System.out.println("Name: "+ this.name);
		System.out.println("Age: "+ this.age);
		System.out.println("Salary: "+ this.salary);
		System.out.println("Designation: "+ this.designation);
	}
	
	protected void raise() {
		if (this.designation.equals("Clerk"))
			this.salary += 2000;
		else if (this.designation.equals("Programmer"))
			this.salary += 5000;
		else if (this.designation.equals("Manager"))
			this.salary += 15000;
	}
}

class Clerk extends Employee {
	Clerk(String name, int age) {
		setName(name);
		setAge(age);
		setSalary(20000);
		setDesignation("Clerk");
	}
}

class Programmer extends Employee {
	Programmer(String name, int age) {
		setName(name);
		setAge(age);
		setSalary(30000);
		setDesignation("Programmer");
	}
}

class Manager extends Employee {
	Manager(String name, int age) {
		setName(name);
		setAge(age);
		setSalary(100000);
		setDesignation("Manager");
	}
}

public class EmployeeManagementApp extends Employee {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int key = 0;
		
		Employee employeeDetails[] = new Employee[100];
		
		int n = 0;
		
		String name;
		int age;
		
		int createKey = 0;
		
		while(true) {
			System.out.println("1. Create");
			System.out.println("2. Display");
			System.out.println("3. Raise Salary");
			System.out.println("4. Exit");
			
			key = sc.nextInt();
			
			if (key == 1) {
				System.out.println();
				System.out.println("1. Clerk");
				System.out.println("2. Programmer");
				System.out.println("3. Manager");
				System.out.println("4. Exit");
				
				createKey = sc.nextInt();
				sc.nextLine();
				
				if (createKey == 1 || createKey == 2 || createKey == 3) {
					System.out.print("Enter the name of the employee: ");
					name = sc.nextLine();
					System.out.print("Enter the age of the employee: ");
					age = sc.nextInt();
					sc.nextLine();
					if (createKey == 1)
						employeeDetails[n] = new Clerk(name, age);
					else if (createKey == 2)
						employeeDetails[n] = new Programmer(name, age);
					else
						employeeDetails[n] = new Manager(name, age);
					n++;
				}
				else if (createKey == 4)
					continue;
				else
					System.out.println("Invalid Input");
			}
			else if (key == 2)
				for(int i = 0; i < n ; i++) {
					Employee emp = employeeDetails[i];
					emp.display();
				}
			else if (key == 3)
				for (int i = 0; i < n ; i++) {
					Employee emp = employeeDetails[i];
					emp.raise();
				}
			else if (key == 4)
				break;
			else
				System.out.println("Invalid Input");
		}
		
		sc.close();
	}
}

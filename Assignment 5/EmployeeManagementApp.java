import java.util.Scanner;
import java.util.regex.*;
import java.util.*;

class NameException extends RuntimeException {
	public NameException() {
		super();
	}
	public NameException(String msg) {
		super(msg);
	}
}

class AgeException extends RuntimeException {
	public AgeException() {
		super();
	}
	public AgeException(String msg) {
		super(msg);
	}
}

abstract class Employee {

	static int count = 0;
	private int eid;
	private String name;
	private int age;
	private float salary;
	private String designation;

	public int getEid() {
		return this.eid;
	}

	public float getSalary() {
		return this.salary;
	}

	public void setEid() {
		this.eid = count;
		count++;
	}

	public void setName() {
		this.name = Menu.readName();
	}

	public void setAge() {
		this.age = Menu.readAge(21, 60);
	}

	public void setSalary(float salary) {
		while(true) {
			if (salary >= 20000) {
				this.salary = salary;
				break;
			}
			else
				System.out.println("Please enter a valid salary then continue.");
		}
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	final public void display() {
		System.out.println("Employee ID: " + this.eid);
		System.out.println("Name: " + this.name);
		System.out.println("Age: " + this.age);
		System.out.println("Salary: " + this.salary);
		System.out.println("Designation: " + this.designation);
	}

	abstract public void raiseSalary();
}

final class Clerk extends Employee {
	Clerk() {
		setEid();
		setName();
		setAge();
		setSalary(20000);
		setDesignation("Clerk");
	}

	public void raiseSalary() {
		float s = this.getSalary();
		this.setSalary(s + 2000);
	}
}

final class Programmer extends Employee {
	Programmer() {
		setEid();
		setName();
		setAge();
		setSalary(30000);
		setDesignation("Programmer");
	}

	public void raiseSalary() {
		float s = this.getSalary();
		this.setSalary(s + 3000);
	}

}

final class Manager extends Employee {
	Manager() {
		setEid();
		setName();
		setAge();
		setSalary(100000);
		setDesignation("Manager");
	}

	public void raiseSalary() {
		float s = this.getSalary();
		this.setSalary(s + 15000);
	}

}

class Menu {
	public static int readChoice(int maxChoice) {
		Scanner sc = new Scanner(System.in);
		int choice;
		try {
			choice = sc.nextInt();
			sc.nextLine();
			if (choice > maxChoice) {
				System.out.println("Please choose between 1 and " + maxChoice + ".");
				return 0;
			}
		} catch(InputMismatchException e) {
			e.printStackTrace();
			return 0;
		}
		return choice;
	}

	public static String readName() {
		Scanner sc = new Scanner(System.in);
		String name = "";
		while(true) {
			System.out.print("Enter the name of the employee: ");
			try {
				name = sc.nextLine();
				Pattern p1 = Pattern.compile("[A-Z][a-z]*[ ][A-Z][a-z]*");
				Matcher m1 = p1.matcher(name);
				if (m1.matches() == false) {
					throw new NameException("Invalid input for name. Please enter alphabets only in correct order.");
				}

			} catch(NameException e) {
				System.out.println(e.getMessage());
				continue;
			}
			return name;
		}
	}

	public static int readAge(int left, int right) {
		Scanner sc = new Scanner(System.in);
		int age = 0;
		while(true) {
			System.out.print("Enter the age of the employee: ");
			try {
				age = sc.nextInt();
				sc.nextLine();
				if ( age < left || age > right )
					throw new AgeException("Age should be between " + left + " and " + right + ".");

			} catch(InputMismatchException e) {
				e.printStackTrace();
				continue;
			} catch(AgeException e) {
				System.out.println(e.getMessage());
				continue;
			}
			return age;
		}
	}
}

public class EmployeeManagementApp {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Employee employeeDetails[] = new Employee[100];
		
		int n = 0;

		int key = 0, createKey = 0;
		
		while (true) {
			System.out.println("1. Create");
			System.out.println("2. Display");
			System.out.println("3. Raise Salary");
			System.out.println("4. Remove");
			System.out.println("5. Exit");
			
			key = Menu.readChoice(5);

			if (key == 1) {
				System.out.println();
				System.out.println("1. Clerk");
				System.out.println("2. Programmer");
				System.out.println("3. Manager");
				System.out.println("4. Exit");
					
				createKey = Menu.readChoice(4);
				
				if (createKey == 1)
					employeeDetails[n++] = new Clerk();
				else if (createKey == 2)	
					employeeDetails[n++] = new Programmer();
				else if (createKey == 3)
					employeeDetails[n++] = new Manager();
				else
					continue;
			} else if (key == 2)
				for (int i = 0; i < n; i++) {
					Employee emp = employeeDetails[i];
					emp.display();
				}
			else if (key == 3)
				for (int i = 0; i < n; i++) {
					Employee emp = employeeDetails[i];
					emp.raiseSalary();
				}
			else if (key == 4) {
				System.out.print("Enter the id of the employee you want to remove: ");
				int id;
				id = sc.nextInt();
				sc.nextLine();
				int emp = -1;
				Employee em = null;
				for (int i = 0; i < n; i++) {
					em = employeeDetails[i];
					if (em.getEid() == id)
						emp = i;
				}
				if (emp == -1)
					System.out.println("No Employee with Employee ID " + id);
				else {
					System.out.print("Do you really want to remove this employee record (Y/N) : ");
					String choice;
					choice = sc.nextLine();
					if (choice.equals("Y")) {
						employeeDetails[emp] = employeeDetails[n - 1];
						employeeDetails[n - 1] = null;
						n--;
					}
				}
			}
			else
				break;
		}
	
		sc.close();
	}
}

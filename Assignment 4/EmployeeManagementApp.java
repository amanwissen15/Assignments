package p1;
import java.util.Scanner;

abstract class Employee {
	private int eid;
	private String name;
	private int age;
	private int salary;
	private String designation;

	public int getEid() {
		return this.eid;
	}

	public int getSalary() {
		return this.salary;
	}

	public void setEid(int eid) {
		if (eid > 0)
			this.eid = eid;
		else
			System.out.println("Please enter a valid Employee ID then continue.");
	}

	public void setName(String name) {
		if (name.length() > 0)
			this.name = name;
		else
			System.out.println("Please enter a name then continue.");
	}

	public void setAge(int age) {
		if (age >= 21 && age <= 60)
			this.age = age;
		else
			System.out.println("Age must be between 21 and 60.");
	}

	public void setSalary(int salary) {
		if (salary >= 20000)
			this.salary = salary;
		else
			System.out.println("Please enter a valid salary then continue.");
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	final public void display() {
		System.out.println("Name: " + this.name);
		System.out.println("Age: " + this.age);
		System.out.println("Salary: " + this.salary);
		System.out.println("Designation: " + this.designation);
	}

	abstract public void raiseSalary();
}

final class Clerk extends Employee {
	Clerk(int eid, String name, int age) {
		setEid(eid);
		setName(name);
		setAge(age);
		setSalary(20000);
		setDesignation("Clerk");
	}

	public void raiseSalary() {
		int s = this.getSalary();
		this.setSalary(s + 2000);
	}
}

final class Programmer extends Employee {
	Programmer(int eid, String name, int age) {
		setEid(eid);
		setName(name);
		setAge(age);
		setSalary(30000);
		setDesignation("Programmer");
	}

	public void raiseSalary() {
		int s = this.getSalary();
		this.setSalary(s + 3000);
	}

}

final class Manager extends Employee {
	Manager(int eid, String name, int age) {
		setEid(eid);
		setName(name);
		setAge(age);
		setSalary(100000);
		setDesignation("Manager");
	}

	public void raiseSalary() {
		int s = this.getSalary();
		this.setSalary(s + 15000);
	}

}

public class EmployeeManagementApp {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int key = 0;
		
		Employee employeeDetails[] = new Employee[100];
		
		int n = 0;
		
		int eid;
		String name;
		int age;
		
		int createKey = 0;
		
		while (true) {
			System.out.println("1. Create");
			System.out.println("2. Display");
			System.out.println("3. Raise Salary");
			System.out.println("4. Remove");
			System.out.println("5. Exit");
			
			try {
				key = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input for choice. Please enter a number.");
				continue;
			}
			
			if (key == 1) {
				System.out.println();
				System.out.println("1. Clerk");
				System.out.println("2. Programmer");
				System.out.println("3. Manager");
				System.out.println("4. Exit");
				
				try {
					createKey = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid input for choice. Please enter a number.");
					continue;
				}
				
				if (createKey == 1 || createKey == 2 || createKey == 3) {
					System.out.print("Enter the ID of the employee: ");
					try {
						eid = Integer.parseInt(sc.nextLine());
					} catch (Exception e) {
						System.out.println("Invalid input for ID. Please enter a number.");
						continue;
					}
					System.out.print("Enter the name of the employee: ");
					name = sc.nextLine();
					System.out.print("Enter the age of the employee: ");
					try {
						age = Integer.parseInt(sc.nextLine());
						if (age < 21 || age > 60) {
							System.out.println("Age must be between 21 and 60.");
							continue;
						}
					} catch (Exception e) {
						System.out.println("Invalid input for age. Please enter a valid number.");
						continue;
					}
					
					int a = 0;
					Employee emp = null;
					for (int i = 0; i < n; i++) {
						emp = employeeDetails[i];
						if (emp.getEid() == eid) {
							a++;
							break;
						}
					}
					if (a == 0) {
						if (createKey == 1)
							employeeDetails[n] = new Clerk(eid, name, age);
						else if (createKey == 2)
							employeeDetails[n] = new Programmer(eid, name, age);
						else
							employeeDetails[n] = new Manager(eid, name, age);
						n++;
					} else
						System.out.println("Employee with Employee ID " + eid + " already present.");
				} else if (createKey == 4)
					continue;
				else
					System.out.println("Invalid Input");
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
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid input for ID. Please enter a valid number.");
					continue;
				}
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
			} else if (key == 5)
				break;
			else
				System.out.println("Invalid Input");
		}
		
		sc.close();
	}
}

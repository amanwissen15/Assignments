import java.util.*;
import java.util.regex.*;

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
    private String name;
    private int age;
    private float salary;
    private String designation;

    public float getSalary() {
        return this.salary;
    }

    public void setName() {
        this.name = Menu.readName();
    }

    public void setAge() {
        this.age = Menu.readAge(21, 60);
    }

    public void setSalary(float salary) {
        while (true) {
            if (salary >= 20000) {
                this.salary = salary;
                break;
            } else {
                System.out.println("Please enter a valid salary then continue.");
            }
        }
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
    private Clerk() {
        setName();
        setAge();
        setSalary(20000);
        setDesignation("Clerk");
    }

    public static Clerk getObject() {
        if (CEO.checkCEO() == null) {
            System.out.println("Create a CEO first.");
            return null;
        }
        return new Clerk();
    }

    public void raiseSalary() {
        float s = this.getSalary();
        this.setSalary(s + 2000);
    }
}

final class Programmer extends Employee {
    private Programmer() {
        setName();
        setAge();
        setSalary(30000);
        setDesignation("Programmer");
    }

    public static Programmer getObject() {
        if (CEO.checkCEO() == null) {
            System.out.println("Create a CEO first.");
            return null;
        }
        return new Programmer();
    }

    public void raiseSalary() {
        float s = this.getSalary();
        this.setSalary(s + 3000);
    }
}

final class Manager extends Employee {
    private Manager() {
        setName();
        setAge();
        setSalary(100000);
        setDesignation("Manager");
    }

    public static Manager getObject() {
        if (CEO.checkCEO() == null) {
            System.out.println("Create a CEO first.");
            return null;
        }
        return new Manager();
    }

    public void raiseSalary() {
        float s = this.getSalary();
        this.setSalary(s + 15000);
    }
}

final class CEO extends Employee {
    private static CEO c1 = null;

    private CEO() {
        setName();
        setAge();
        setSalary(200000);
        setDesignation("CEO");
    }

    public static CEO getObject() {
        if (c1 == null)
            c1 = new CEO();
        return c1;
    }

    public static CEO checkCEO() {
        return c1;
    }

    public void raiseSalary() {
        float s = this.getSalary();
        this.setSalary(s + 50000);
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
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input. Please enter a number.");
            return 0;
        }
        return choice;
    }

    public static String readName() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        while (true) {
            System.out.print("Enter the name of the employee: ");
            try {
                name = sc.nextLine();
                Pattern p1 = Pattern.compile("[A-Z][a-z]* [A-Z][a-z]*");
                Matcher m1 = p1.matcher(name);
                if (!m1.matches()) {
                    throw new NameException("Invalid input for name. Please enter alphabets only in correct order.");
                }
            } catch (NameException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return name;
        }
    }

    public static int readAge(int left, int right) {
        Scanner sc = new Scanner(System.in);
        int age = 0;
        while (true) {
            System.out.print("Enter the age of the employee: ");
            try {
                age = sc.nextInt();
                sc.nextLine();
                if (age < left || age > right)
                    throw new AgeException("Age should be between " + left + " and " + right + ".");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            } catch (AgeException e) {
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

        Map<Integer, Employee> employeeMap = new HashMap<>();

        int key = 0, createKey = 0;

        while (true) {
            System.out.println("1. Create");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Remove");
            System.out.println("5. Search");
            System.out.println("6. Exit");

            key = Menu.readChoice(6);

            if (key == 1) {
                System.out.println();
                System.out.print("Enter the ID for the new employee: ");
                int id = sc.nextInt();
                sc.nextLine();
                if (employeeMap.containsKey(id)) {
                    System.out.println("An employee with this ID already exists.");
                    continue;
                }

                System.out.println("1. Clerk");
                System.out.println("2. Programmer");
                System.out.println("3. Manager");
                System.out.println("4. CEO");
                System.out.println("5. Exit");

                createKey = Menu.readChoice(5);

                if (createKey == 1) {
                    Clerk c = Clerk.getObject();
                    if (c != null)
                        employeeMap.put(id, c);
                } else if (createKey == 2) {
                    Programmer p = Programmer.getObject();
                    if (p != null)
                        employeeMap.put(id, p);
                } else if (createKey == 3) {
                    Manager m = Manager.getObject();
                    if (m != null)
                        employeeMap.put(id, m);
                } else if (createKey == 4) {
                    CEO c = CEO.getObject();
                    if (c != null)
                        employeeMap.put(id, c);
                } else
                    continue;

            } 
	    else if (key == 2) {
                for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
                    System.out.println("Employee ID: " + entry.getKey());
                    entry.getValue().display();
                }
            } 
	    else if (key == 3) {
                for (Employee emp : employeeMap.values()) {
                    emp.raiseSalary();
                }
            } 
	    else if (key == 4) {
                System.out.print("Enter the ID of the employee you want to remove: ");
                int id = sc.nextInt();
                sc.nextLine();
                if (employeeMap.containsKey(id)) {
                    System.out.print("Do you really want to remove this employee record (Y/N): ");
                    String choice = sc.nextLine();
                    if (choice.equalsIgnoreCase("Y")) {
                        employeeMap.remove(id);
                        System.out.println("Employee removed.");
                    }
                } else {
                    System.out.println("No Employee with ID " + id);
                }
            } 
	    else if (key == 5) {
		System.out.println();
                System.out.print("Enter the ID of the employee: ");
                int id = sc.nextInt();
                sc.nextLine();
                if (employeeMap.containsKey(id)) {
		    Employee emp = employeeMap.get(id);
		    System.out.println("Employee found:");
		    emp.display();
		} else {
		    System.out.println("No Employee with ID " + id + " exists.");
		}
	    }
	    else
		break;
	}
	
	sc.close();
    }
}

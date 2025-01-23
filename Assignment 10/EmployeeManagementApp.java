import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    static String records = "employees.csv";
    static private int id = 0;
    
    protected Employee(String name, int age, int salary, String designation) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(records, true))) {
	        id++;
            writer.write(String.valueOf(id));
            writer.write(", ");
            writer.write(name);
            writer.write(", ");
            writer.write(String.valueOf(age));
            writer.write(", ");
            writer.write(String.valueOf(salary));
            writer.write(", ");
            writer.write(designation);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void setID() {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                id = Integer.parseInt(fields[0].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

final class Clerk extends Employee {
    private Clerk(String name, int age) {
        super(name, age, 20000, "Clerk");
    }

    public static void getObject(String name, int age) {
        if (CEO.checkCEO() == false)
            System.out.println("Create a CEO first.");
        else
            new Clerk(name, age);
    }

    public static void raiseSalary() {
        String records = "employees.csv";
        String tempRecords = "employees_temp.csv";
        
        List<String[]> rows = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(records))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                rows.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (String[] row : rows) {
            String designation = row[4].trim();
            if (designation.equals("Clerk")) {
                int currentSalary = Integer.parseInt(row[3].trim());
                row[3] = String.valueOf(currentSalary + 2000);
            }
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempRecords))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File oldFile = new File(records);
        File newFile = new File(tempRecords);
        
        oldFile.delete();
        newFile.renameTo(oldFile);
        
    }
}

final class Programmer extends Employee {
    private Programmer(String name, int age) {
        super(name, age, 30000, "Programmer");
    }

    public static void getObject(String name, int age) {
        if (CEO.checkCEO() == false)
            System.out.println("Create a CEO first.");
        else
            new Programmer(name, age);
    }

    public static void raiseSalary() {
        String records = "employees.csv";
        String tempRecords = "employees_temp.csv";
        
        List<String[]> rows = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(records))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                rows.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (String[] row : rows) {
            String designation = row[4].trim();
            if (designation.equals("Programmer")) {
                int currentSalary = Integer.parseInt(row[3].trim());
                row[3] = String.valueOf(currentSalary + 3000);
            }
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempRecords))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File oldFile = new File(records);
        File newFile = new File(tempRecords);
        
        oldFile.delete();
        newFile.renameTo(oldFile);
        
    }
}

final class Manager extends Employee {
    private Manager(String name, int age) {
        super(name, age, 100000, "Manager");
    }

    public static void getObject(String name, int age) {
        if (CEO.checkCEO() == false)
            System.out.println("Create a CEO first.");
        else
            new Manager(name, age);
    }

    public static void raiseSalary() {
        String records = "employees.csv";
        String tempRecords = "employees_temp.csv";
        
        List<String[]> rows = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(records))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                rows.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (String[] row : rows) {
            String designation = row[4].trim();
            if (designation.equals("Manager")) {
                int currentSalary = Integer.parseInt(row[3].trim());
                row[3] = String.valueOf(currentSalary + 15000);
            }
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempRecords))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File oldFile = new File(records);
        File newFile = new File(tempRecords);
        
        oldFile.delete();
        newFile.renameTo(oldFile);
        
    }
}

final class CEO extends Employee {
    private static boolean c1 = false;

    private CEO(String name, int age) {
        super(name, age, 200000, "CEO");
    }

    public static void getObject(String name, int age) {
        if (c1 == false) {
            new CEO(name, age);
            c1 = true;
        }
        else
            System.out.println("CEO already exists.");
    }
    
    public static void setCEO() {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String designation = fields[4].trim();
                if (designation.equals("CEO")) {
                    c1 = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCEO() {
        return c1;
    }

    public static void raiseSalary() {
        String records = "employees.csv";
        String tempRecords = "employees_temp.csv";
        
        List<String[]> rows = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(records))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                rows.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (String[] row : rows) {
            String designation = row[4].trim();
            if (designation.equals("CEO")) {
                int currentSalary = Integer.parseInt(row[3].trim());
                row[3] = String.valueOf(currentSalary + 50000);
            }
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempRecords))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File oldFile = new File(records);
        File newFile = new File(tempRecords);
        
        oldFile.delete();
        newFile.renameTo(oldFile);
        
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

        int key = 0, createKey = 0;
        
        Employee.setID();
        CEO.setCEO();

        while (true) {
            System.out.println("1. Create");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Remove");
            System.out.println("5. Search");
            System.out.println("6. Exit");

            key = Menu.readChoice(6);

            if (key == 1) {
                
                String name = Menu.readName();
                int age = Menu.readAge(21, 60);

                System.out.println("1. Clerk");
                System.out.println("2. Programmer");
                System.out.println("3. Manager");
                System.out.println("4. CEO");
                System.out.println("5. Exit");

                createKey = Menu.readChoice(5);

                if (createKey == 1)
                    Clerk.getObject(name, age);
                else if (createKey == 2)
                    Programmer.getObject(name, age);
                else if (createKey == 3)
                    Manager.getObject(name, age);
                else if (createKey == 4)
                    CEO.getObject(name, age);
                else
                    continue;

            }
    	    else if (key == 2) {
    	        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split(",");
                        
    		            int id = Integer.parseInt(fields[0].trim());
                        String name = fields[1].trim();
                        int age = Integer.parseInt(fields[2].trim());
                        int salary = Integer.parseInt(fields[3].trim());
                        String designation = fields[4].trim();
    
    		            System.out.println();
    		            System.out.println("ID: " + id);
                        System.out.println("Name: " + name);
                        System.out.println("Age: " + age);
                        System.out.println("Salary: " + salary);
                        System.out.println("Designation: " + designation);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
    	    }
    	    else if (key == 3) {
            	Clerk.raiseSalary();
            	Programmer.raiseSalary();
            	Manager.raiseSalary();
            	CEO.raiseSalary();
            } 
    	    else if (key == 4) {
                    System.out.print("Enter the ID of the employee you want to remove: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    String records = "employees.csv";
                    String tempRecords = "employees_temp.csv";
		            int a = 0;
                    
                    List<String[]> rows = new ArrayList<>();
                    
                    try (BufferedReader br = new BufferedReader(new FileReader(records))) {
                        String line;
            
                        while ((line = br.readLine()) != null) {
                            String[] fields = line.split(",");
                            int idTemp = Integer.parseInt(fields[0].trim());
                            if (idTemp != id)
                                rows.add(fields);
			                else
				                a++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    if (a == 0) {
                        System.out.println("No employee with ID: " + id);
                        continue;
                    }
                    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempRecords))) {
                        for (String[] row : rows) {
                            bw.write(String.join(",", row));
                            bw.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    File oldFile = new File(records);
                    File newFile = new File(tempRecords);
                    
                    oldFile.delete();
                    newFile.renameTo(oldFile);
                } 
    	    else if (key == 5) {
            	System.out.println();
                System.out.print("Enter the ID of the employee: ");
                int id = sc.nextInt();
                sc.nextLine();
                int a = 0;
                try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split(",");
                        
    		            int idTemp = Integer.parseInt(fields[0].trim());
    		            if (idTemp == id) {
                            String name = fields[1].trim();
                            int age = Integer.parseInt(fields[2].trim());
                            int salary = Integer.parseInt(fields[3].trim());
                            String designation = fields[4].trim();
            
            	            System.out.println();
            	            System.out.println("Employee with ID " + id + " found.");
                            System.out.println("Name: " + name);
                            System.out.println("Age: " + age);
                            System.out.println("Salary: " + salary);
                            System.out.println("Designation: " + designation);
                            a++;
                            break;
    		            }
                    }
                    if (a == 0)
                        System.out.println("No Employee with ID: " + id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    	    }
    	    else
    		    break;
	    }
	
	    sc.close();
    }
}

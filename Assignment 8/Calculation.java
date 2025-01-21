import java.util.*;
import java.lang.reflect.*;

class Calculator {
	public int add ( int a, int b ) {
		return a + b;
	}
	public int sub ( int a, int b ) {
		return a - b;
	}
	public int mul ( int a, int b ) {
		return a * b;
	}
	public int div ( int a, int b ) {
		return a / b;
	}
}

public class Calculation {
	public static void main(String args[]) {
		Calculator c = new Calculator();
		Scanner sc = new Scanner(System.in);
		while ( true ) {
			System.out.println("--------------------------Enter 'q' to exit--------------------------");
			String operation;
			System.out.print("Enter the Operation: ");
			operation = sc.nextLine();
			if ( operation.equals("q") )
				break;
			int op1, op2;
			System.out.print("Enter the first Operand: ");
			op1 = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the second Operand: ");
			op2 = sc.nextInt();
			sc.nextLine();
			try {
				Class<?> c1 = Class.forName("Calculator");
				Method method = c1.getMethod( operation, int.class, int.class );
				System.out.println("Result of operation " + operation + " on parameters: " + method.invoke(c, op1, op2));
			} catch ( Exception e ) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		sc.close();
	}
}
package demo.opflow;

import java.util.Scanner;

public class DemoStringsInSwitch {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your month of birth (as a string): ");
		String month = scanner.nextLine();
		
		switch (month.toLowerCase()) {
		
			case "december":
			case "january":
			case "february":
				System.out.println("You were born in winter.");
				break;
				
			case "march":
			case "april":
			case "may":
				System.out.println("You were born in spring.");
				break;
				
			case "june":
			case "july":
			case "august":
				System.out.println("You were born in summer.");
				break;

			case "september":
			case "october":
			case "november":
				System.out.println("You were born in autumn.");
				break;
				
			default:
				System.out.println("I have no idea when you were born!");
				break;
		}
		
		scanner.close();
	}
}

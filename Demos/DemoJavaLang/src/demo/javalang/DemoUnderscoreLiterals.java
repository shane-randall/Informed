package demo.javalang;

public class DemoUnderscoreLiterals {

	public static void main(String[] args) {

		// Use underscores to emphasize thousands grouping;
		long salary = 123_456_789;
		int tenK = 10_000;
		long c = 299_792_458;
		double pi = 3.141_592_653_589;

		System.out.printf("Salary=%d, 10K=%d, c=%d, pi=%.15f\n", salary, tenK, c, pi);
		
		// You can use underscores anywhere if you really want to! (except at start and end of a number).
		int myDob = 1964_12_03;
		double myTelNum = 1_234_567890;
		
		System.out.printf("My date of birth=%d, my tel num=%.0f\n", myDob, myTelNum);
	}
}

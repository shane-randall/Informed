package demo.javalang;

public class DemoBinaryLiterals {

	public static void main(String[] args) {
		simpleDemo();
		testBitsDemo(1);
		testBitsDemo(5);
		testBitsDemo(23);
	}

	public static void simpleDemo() {

		// An 8-bit 'byte' value:
		byte aByte = (byte) 0b00100001;

		// A 16-bit 'short' value:
		short aShort = (short) 0b1010000101000101;

		// Some 32-bit 'int' values:
		int anInt1 = 0b10100001010001011010000101000101;
		int anInt2 = 0b101;
		int anInt3 = 0B101; // The B can be upper or lower case.

		// A 64-bit 'long' value. Note the "L" suffix:
		long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;

		System.out.printf("%d %d %d %d %d %d\n", aByte, aShort, anInt1, anInt2, anInt3, aLong);
	}

	
	public static void testBitsDemo(int number) {

		String str;
		switch (number & 0b00000111) {
			case 0b00000001:
				str = "Bit 8";
				break;
			case 0b00000010:
				str = "Bit 7";
				break;
			case 0b00000011:
				str = "Bits 7 and 8";
				break;
			case 0b00000100:
				str = "Bit 6";
				break;
			case 0b00000101:
				str = "Bits 6 and 8";
				break;
			case 0b00000110:
				str = "Bits 6 and 7";
				break;
			case 0b00000111:
				str = "Bits 6, 7, and 8";
				break;
			default:
				str = "No bits";
				break;
		}
		System.out.printf("Bits 6/7/8 in %d: %s\n", number, str);
	}
}

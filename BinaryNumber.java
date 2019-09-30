// Author: Puzhuo Li 
// CS570: HW01
// CWID:10439435

import java.util.Arrays;

public class BinaryNumber{
	// Data fields
	private int[] data;
	private boolean overflow;
	
	// Methods
	// Constructor01
	// Creating a binary number of length length and consisting only of zeros.
	public BinaryNumber(int length) {
		if (length>=0) {
			data = new int[length];
			for (int i = 0; i<length; i++) {
				data[i] = 0;
			}
		}
		else {
			System.out.println("Please enter a valid number");
		}
	}
	// Constructor02
	// Creating a binary number given a string
	public BinaryNumber(String str) {
		data = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != 48 && ch != 49) {
				System.out.println("Please enter a valid string");
				break;
				
			}
			else {
				data[i] = Character.getNumericValue(ch) ;
			}
		}
		
	}
	
	// Determining the length of a binary number
	public int getLength() {
		int res = 0;
		try {
			res = data.length;
		}catch (NullPointerException e) {
			System.out.println("Please double-check the binary number");
		}
		return res;
	}
	
	// Obtaining a digit of a binary number given an index
	// if the index is out of range, the message is shown on the screen.
	public int getDigit(int index) {
		int digit = 0;
		try {
			digit = data[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index is out of bounds");
		}
		return digit;
	}
}

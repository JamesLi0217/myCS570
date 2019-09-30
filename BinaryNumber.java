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
	
	// Transforming a binary number to its decimal notation
	public int toDecimal() {
		int result = 0;
		for (int i = 0; i < data.length; i++) {
			result += data[i]*(Math.pow(2, i));
		}
		return result; 
	}
	
	// Shifting all digits in a binary number any number of places to the right,
	public void shiftR(int amount) {
		int[] shift = new int[amount+data.length];
		for (int i = 0; i < data.length; i++) {
			shift[i+amount] = data[i];
		}
		data = shift;
	}
	
	// Adding two binary numbers
	public void add(BinaryNumber aBinaryNumber) {
		if (aBinaryNumber.getLength() != data.length) {
			System.out.println("The length of the binary numbers do not coincide");
		}
		else {
			int[] result = new int[data.length+1];
			int temp = 0;
			for (int i = 0; i < data.length; i++) {
				if (data[i] + aBinaryNumber.data[i] + temp >= 2) {
					result[i] = data[i] + aBinaryNumber.data[i] + temp - 2;
					temp = 1; 
				}
				else {
					result[i] = data[i] + aBinaryNumber.data[i] + temp;
					temp = 0;
				}	
			}
			// determine whether overflow
			if (data[data.length-1] != 0) {
				overflow = true;
			}
			else {
				overflow = false;
			}
			// delete the tailing 0
			if (overflow == false) {
				result = Arrays.copyOfRange(result, 0, result.length-1);
			}
			data = result;
		}
	}
	
	//  clears the overflow flag
	public void clearflow() {
		overflow = false;
	}
	
	//  Transforming a binary number to a String
	public String toString() {
		//  return string when overflow == false; or “Overflow” when overflow == true;
		if (overflow) {
			return "Overflow";
		}
		else {
			return Arrays.toString(data);
		}
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		BinaryNumber bn1 = new BinaryNumber(-8);
		BinaryNumber bn2 = new BinaryNumber("1011");
		BinaryNumber bn3 = new BinaryNumber("1011");
		BinaryNumber bn4 = new BinaryNumber(4);
		System.out.println(bn1);
		System.out.println(bn1.getLength());
		System.out.println(bn3.getDigit(8));
		System.out.println(bn3.toDecimal());
		System.out.println(bn3);
		bn3.shiftR(1);
		System.out.println(bn3);
		bn3.add(bn2);
		System.out.println(bn3.overflow);
		bn3.clearflow();
		System.out.println(bn3.overflow);
		System.out.println(bn3);
	}

}

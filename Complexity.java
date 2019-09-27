// Author: Puzhuo Li 
// CS570: HW02
// CWID:10439435

public class Complexity
{
	// fields
	public static int count = 1;
	// time complexity is O(n^2)
	public static void method01(int n)
	{

		int counter = 1;
		for (int i = 1; i < n; i++)
		{
			for (int j = 1; j < n; j++)
			{
				System.out.println(counter);
				counter += 1;
			}
		}

	}

	// time complexity is O(n^3)
	public static void method02(int n)
	{

		int counter = 1;
		for (int i = 1; i < n; i++)
		{
			for (int j = 1; j < n; j++)
			{
				for (int k = 1; k < n; k++)
				{
					System.out.println(counter);
					counter += 1;
				}
			}
		}
	}

	// time complexity is O(log(n))
	public static void method03(int n)
	{

		int counter = 1;
		for (int i = 1; i < n; i *= 2)
		{
			System.out.println(counter);
			counter += 1;
		}

	}

	// time complexity is O(nlog(n))
	public static void method04(int n)
	{

		int counter = 1;
		for (int i = 1; i < n; i++)
		{
			for (int j = 1; j < n; j *= 2)
			{
				System.out.println(counter);
				counter += 1;
			}
		}

	}

	// time complexity is O(log(log(n)))
	public static void method05(int n)
	{

		int counter = 1;
		for (int i = 2; i < n; i *= i)
		{
			System.out.println(counter);
			counter += 1;
		}

	}

	// time complexity is O(2^n))
	public static int method06(int n)
	{
		if (n == 0)
		{
			return 0;
		} else if (n == 1)
		{
			return 1;
		}
		System.out.println(count);
		count += 1;
		return method06(n - 1) + method06(n - 2);

	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("O(n^2)");
		Complexity.method01(10);

		System.out.println("O(n^3)");
		Complexity.method02(10);

		System.out.println("O(log(n))");
		Complexity.method03(10);

		System.out.println("O(nlog(n))");
		Complexity.method04(10);

		System.out.println("O(log(log(n)))");
		Complexity.method05(10);

		System.out.println("O(2^n)");
		Complexity.method06(10);
	}

}

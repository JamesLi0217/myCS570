import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IDLListTest
{
	// test add element with index
	@Test
	public void addIndexTest()
	{
		IDLList<Integer> list = new IDLList<>();
		assertTrue(list.add(0, 3));
		assertTrue(list.add(1, 4));
		assertEquals(list.toString(), "34");
		assertEquals(list.size(),2);
	}

	// test add element at head
	@Test
	public void addHeadTest()
	{
		IDLList<Integer> list = new IDLList<>();
		assertTrue(list.add(3));
		assertTrue(list.add(-3));
		assertEquals(list.toString(), "-33");
		assertEquals(list.size(),2);

	}

	// test add element at tail
	@Test
	public void appendTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.append(1);
		list.append(3);
		assertEquals(list.toString(), "13");
		assertEquals(list.size(),2);
	}

	// test get element with index
	@Test
	public void getTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.append(1);
		list.append(3);
		assertEquals(list.size(),2);
		assertEquals(list.get(0).toString(), "1");
		assertEquals(list.get(1).toString(), "3");
		assertEquals(list.get(-1), null);
		assertEquals(list.get(100), null);
	}

	// test get element at head
	@Test
	public void getHeadTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.add(1);
		assertEquals(list.getHead().toString(), "1");
		list.add(-3);
		assertEquals(list.getHead().toString(), "-3");
		assertEquals(list.size(),2);
	}

	// test get last element
	@Test
	public void getLastTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.add(1);
		assertEquals(list.getLast().toString(), "1");
		list.add(-3);
		assertEquals(list.getLast().toString(), "1");
		assertEquals(list.size(),2);
	}

	// test get size
	@Test
	public void sizeTest()
	{
		IDLList<Integer> list = new IDLList<>();
		assertEquals(list.size(), "0");
		list.add(1);
		assertEquals(list.size(), "1");
		list.add(-3);
		assertEquals(list.size(), "2");
	}

	// test remove element at head
	@Test
	public void removeTest()
	{
		IDLList<Integer> list = new IDLList<>();
		assertEquals(list.remove(), null);
		list.add(-3);
		assertEquals(list.remove().toString(), "-3");
		assertEquals(list.remove(), null);
		list.add(1);
		list.add(3);
		assertEquals(list.size(),2);
		list.add(4);
		assertEquals(list.remove().toString(), "4");
	}

	// test remove last element at last position
	@Test
	public void removeLastTest()
	{
		IDLList<Integer> list = new IDLList<>();
		assertEquals(list.removeLast(), null);
		list.add(-3);
		assertEquals(list.removeLast().toString(), "-3");
		list.add(1);
		list.add(3);
		list.add(4);
		assertEquals(list.removeLast().toString(), "1");
		assertEquals(list.size(),2);
	}

	// test remove element at index
	@Test
	public void removeAtTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.add(1);
		list.add(3);
		assertEquals(list.size(),2);
		list.add(4);
		assertEquals(list.removeAt(2).toString(), "1");
	}

	// test remove specific element at first occurrence
	@Test
	public void removeElemTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.add(3);
		assertTrue(list.remove(3));
		assertFalse(list.remove(3));
		list.add(3);
		list.add(1);
		assertEquals(list.size(),2);
		list.add(3);
		list.add(4);
		assertTrue(list.remove(3));
		assertFalse(list.remove(6));
		assertEquals(list.size(),3);
	}

	// test list's toString
	@Test
	public void toStringTest()
	{
		IDLList<Integer> list = new IDLList<>();
		list.add(3);
		list.add(3);
		list.add(1);
		list.add(3);
		list.add(4);
		assertEquals(list.size(),5);
		assertEquals(list.toString(), "43133");
	}

	public static void main(String[] args)
	{
		// run all the test cases
		IDLListTest tester = new IDLListTest();
		tester.addHeadTest();
		tester.addIndexTest();
		tester.appendTest();
		tester.getHeadTest();
		tester.getLastTest();
		tester.getTest();
		tester.removeAtTest();
		tester.removeLastTest();
		tester.removeTest();
		tester.removeElemTest();
		System.out.println("All Tests Passed");

	}

}

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TreapTest
{

	@Test
	void test1() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(4,19));
		assertTrue(testTree.add(2,31));
		assertTrue(testTree.add(6,70));
		assertTrue(testTree.add(1,84));
		assertTrue(testTree.add(3,12));
		assertTrue(testTree.add(5,83));
		assertTrue(testTree.add(7,26));
		assertTrue(testTree.find(7));
		assertFalse(testTree.find(90));
		assertTrue(testTree.delete(7));
		assertFalse(testTree.delete(55));
	}
	
	void test2() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(1,12));
		assertTrue(testTree.add(2,22));
		assertTrue(testTree.add(3,28));
		assertTrue(testTree.add(4,84));
		assertTrue(testTree.add(5,69));
		assertTrue(testTree.add(6,96));
		assertTrue(testTree.add(7,76));
		assertTrue(testTree.find(7));
		assertFalse(testTree.find(45));
		assertTrue(testTree.delete(7));
		assertFalse(testTree.delete(0));
	}
	
	void test3() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(10,19));
		assertTrue(testTree.add(75,31));
		assertTrue(testTree.add(47,70));
		assertTrue(testTree.add(35,84));
		assertTrue(testTree.add(32,12));
		assertTrue(testTree.add(51,83));
		assertTrue(testTree.add(78,26));
		assertTrue(testTree.find(78));
		assertFalse(testTree.find(101));
		assertTrue(testTree.delete(47));
		assertFalse(testTree.delete(95));
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		TreapTest test1 = new TreapTest();
		test1.test1();
		test1.test2();
		test1.test3();
		Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	}

}

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * 
 * @author Puzhuo Li 
 * A treap is a binary search tree (BST) which additionally maintains heap priorities
 * @param <E> Integer
 */
public class Treap<E extends Comparable<E>>
{
	/**
	 * a private static inner class Node<E> of the Treap class
	 * 
	 * @param <E>
	 */
	private static class Node<E>
	{
		/**
		 * data key for the search random heap priority left, right child
		 */
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		/**
		 * Constructor
		 * 
		 * @param data
		 * @param priority
		 * @throws IllegalArgumentException
		 */
		public Node(E data, int priority) throws IllegalArgumentException
		{
			if (data == null)
			{
				throw new IllegalArgumentException("Data can't be null");
			}
			this.data = data;
			this.priority = priority;
		}

		// Method
		/**
		 * rotate root node to right
		 * 
		 * @return Node<E>
		 */
		public Node<E> rotateRight()
		{
			Node<E> futureRoot = this.left;
			if (futureRoot != null)
			{
				Node<E> futureRightLeft = futureRoot.right;
				futureRoot.right = this;
				this.left = futureRightLeft;
				return futureRoot;
			}
			return this; // In general it is not used
		}

		/**
		 * rotate root node to left
		 * 
		 * @return Node<E>
		 */
		public Node<E> rotateLeft()
		{
			Node<E> futureRoot = this.right;
			if (futureRoot != null)
			{
				Node<E> futureLeftRight = futureRoot.left;
				futureRoot.left = this;
				this.right = futureLeftRight;
				return futureRoot;
			}
			return this;
		}

		/**
		 * @return String describe node with data, priority
		 */
		@Override
		public String toString()
		{
			return ("(key=" + this.data + ", " + "priority=" + this.priority + ")\n");
		}

	}

	// Fields
	/**
	 * priorityGenerator create a priority by random root is comparable node
	 */
	private Random priorityGenerator;
	private Node<E> root; // E must be Comparable

	/**
	 * Treap Constructors with random priority
	 */
	public Treap()
	{
		this.priorityGenerator = new Random();
	}

	/**
	 * Treap Constructors with random priority
	 * 
	 * @param seed
	 */
	public Treap(long seed)
	{
		this.priorityGenerator = new Random(seed);
	}

	// Methods
	/**
	 * 
	 * @param key node's data
	 * @return true: add successfully
	 */
	public boolean add(E key)
	{
		int priority = this.priorityGenerator.nextInt();
		while (priorityUnique(root, priority) == false)
		{
			priority = this.priorityGenerator.nextInt();
		}
		return add(key, priority);

	}

	/**
	 * 
	 * @param key:      node's data
	 * @param priority: node's priority
	 * @return true: add successfully
	 * 
	 */
	public boolean add(E key, int priority)
	{

		if (priorityUnique(this.root, priority) == false)
		{
			return false;
		}
		// if node with key in the treap already, return false
		if (find(key) == true)
		{
			return false;
		}
		// new a node
		Node<E> newNode = null;
		try
		{
			newNode = new Node<E>(key, priority);
			// might key is null, throws exception
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		// store all parents in path
		Deque<Node<E>> allParents = findParentPath(newNode);
		return insertBST(allParents, newNode) && reHeapUp(allParents, newNode);
	}

	/**
	 * To judge if nodePriority is unique in Treap
	 * 
	 * @param root
	 * @param nodePriority
	 * @return
	 */
	private boolean priorityUnique(Node<E> root, int nodePriority)
	{
		// TODO Auto-generated method stub
		if (root == null)
		{
			return true;
		}
		if (root.priority == nodePriority)
		{
			return false;
		}
		return priorityUnique(root.left, nodePriority) && priorityUnique(root.right, nodePriority);
	}

	/**
	 * To store all parents in Stack
	 * 
	 * @param newNode
	 * @return stack contains all parents in the path
	 */
	private Deque<Node<E>> findParentPath(Node<E> newNode)
	{
		Deque<Node<E>> res = new ArrayDeque<>();
		Node<E> cur = root;
		Node<E> parent = null;
		while (cur != null)
		{
			parent = cur;
			res.offerFirst(parent); // store
			// newNode.data < root.data
			if (newNode.data.compareTo(cur.data) < 0)
			{
				cur = cur.left;
			} else
			{
				cur = cur.right;
			}
		}
		return res;
	}

	/**
	 * adjust treap by node priority when add node into treap
	 * 
	 * @param allParents: stack contains all parents in the path
	 * @param newNode
	 * @return true: reheap successfully
	 */
	private boolean reHeapUp(Deque<Node<E>> allParents, Node<E> newNode)
	{
		while (allParents.size() > 0 && newNode.priority > allParents.peek().priority)
		{
			// parent
			Node<E> parent = allParents.pollFirst();
			// grandparent = parent's parent
			Node<E> grandParent = allParents.peek();
			Node<E> temp = null;
			if (newNode.data.compareTo(parent.data) < 0)
			{
				temp = parent.rotateRight();
			} else
			{
				temp = parent.rotateLeft();
			}
			if (grandParent == null)
			{
				root = temp;
			} else if (grandParent.data.compareTo(parent.data) < 0)
			{
				grandParent.right = temp;
			} else
			{
				grandParent.left = temp;
			}
		}
		return true;
	}

	/**
	 * find right position in BST, insert in leaf
	 * 
	 * @param allParents: stack contains all parents in the path
	 * @param newNode
	 * @return true: insert successfully
	 */
	private boolean insertBST(Deque<Node<E>> allParents, Node<E> newNode)
	{
		// TODO Auto-generated method stub
//		if (priorityUnique(this.root, newNode.priority) == false) {
//			return false;
//		}
		if (root == null)
		{
			root = newNode;
		}
		Node<E> parent = allParents.peekFirst();
		if (parent == null)
		{
			return true;
		}
		if (parent.data.compareTo(newNode.data) < 0)
		{
			parent.right = newNode;
		} else if (parent.data.compareTo(newNode.data) == 0)
		{
			return false;
		} else
		{
			parent.left = newNode;
		}
//		System.out.println(newNode.toString());
		return true;
	}

	/**
	 * To delete node with key
	 * 
	 * @param key
	 * @return true: successfully
	 */
	public boolean delete(E key)
	{
		// key not in treap
		if (find(key) == false)
		{
			return false;
		}
		// key in the treap, now find it
		Node<E> cur = root;
		Node<E> newNode = null;
		Node<E> parent = null;
		while (cur != null)
		{

			// key < root.data
			if (key.compareTo(cur.data) < 0)
			{
				parent = cur;
				cur = cur.left;
			} else if (key.compareTo(cur.data) == 0)
			{ // find key in treap
				cur.priority = Integer.MIN_VALUE; // assign priority to Integer.MIN_VALUE
				break;
			} else
			{
				parent = cur;
				cur = cur.right;
			}

		}

		// rotate target node to leaf then delete it
		while (cur != null)
		{

			// current node is leaf
			if (cur.left == null && cur.right == null)
			{
				if (parent == null)
				{
					root = null;
					return true;
				}
				if (parent.data.compareTo(cur.data) < 0)
				{
					parent.right = null;
				} else if (parent.data.compareTo(cur.data) > 0)
				{
					parent.left = null;
				}
				return true;
			}
			// need continue going to leaf
			else
			{

				if (cur.left == null)
				{
					newNode = cur.rotateLeft();
				} else if (cur.right == null)
				{
					newNode = cur.rotateRight();
				} else if (cur.left.priority > cur.right.priority)
				{
					newNode = cur.rotateRight();
				} else if (cur.left.priority < cur.right.priority)
				{
					newNode = cur.rotateLeft();
				}

				// root is target
				if (parent == null)
				{
					root = newNode;
				}

				else if (parent.data.compareTo(cur.data) < 0)
				{
					parent.right = newNode;
				} else
				{
					parent.left = newNode;
				}
				parent = newNode;
			}
		}
		return true;
	}

	/**
	 * To find node with key in one treap or not
	 * 
	 * @param root
	 * @param key
	 * @return true: find node with key in one treap
	 */
	private boolean find(Node<E> root, E key)
	{
		Node<E> cur = root;
		while (cur != null)
		{
			// newNode.data < root.data
			if (key.compareTo(cur.data) < 0)
			{
				cur = cur.left;
			} else if (key.compareTo(cur.data) == 0)
			{ // find key in treap
				return true;
			} else
			{
				cur = cur.right;
			}
		}
		return false;
	}

	/**
	 * To find node with key in current treap or not Delegation by find(Node<E>
	 * root, E key)
	 * 
	 * @param root
	 * @param key
	 * @return true: find node with key in current treap
	 */
	public boolean find(E key)
	{

		return find(this.root, key);
	}

	/**
	 * @return String to describe tree
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		preOrderTraversal(root, sb, 0);

		return sb.toString();
	}

	/**
	 * To preOrderTraversal this treap like BST DFS
	 * 
	 * @param root
	 * @param sb:    StringBuilder
	 * @param depth: which level
	 */
	private void preOrderTraversal(Node<E> root, StringBuilder sb, int depth)
	{
		for (int i = 0; i <= depth; i++)
		{
			sb.append(" ");
		}
		if (root == null)
		{
			sb.append("null\n");
			return;
		}
		sb.append(root.toString());

		preOrderTraversal(root.left, sb, depth + 1);
		preOrderTraversal(root.right, sb, depth + 1);
	}

	public static void main(String[] args)
	{
		// test
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(null, 19);
		System.out.println(testTree.add(4, 19));
		testTree.add(2, 19);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		System.out.println(testTree.toString());
		testTree.delete(7);
		System.out.println(testTree.toString());
		testTree.delete(2);
		System.out.println(testTree.toString());

	}

}

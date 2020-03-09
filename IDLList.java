
// Author: Puzhuo Li 
// CS570: HW03
// CWID:10439435

import java.util.ArrayList;

public class IDLList<E>
{

	public Node<E> head;
	public Node<E> tail;
	public int size;
	public ArrayList<Node<E>> indices;

	// inner class
	public static class Node<E>
	{
		// fields
		public E data;
		public Node<E> next;
		public Node<E> prev;

		// constructor
		public Node(E elem)
		{
			this.data = elem;
		}

		public Node(E elem, Node<E> prev, Node<E> next)
		{
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	// creates an empty double-linked list.
	public IDLList()
	{
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<>();

	}

	// adds elem at position index (counting from wherever head is). It uses the
	// index for fast access.
	public boolean add(int index, E elem)
	{

		if (index == 0)
		{
			return add(elem);
		}
		if (index < 0 || index > size)
		{
			return false;
		}
		if (index == size)
		{
			return append(elem);
		}

		Node<E> pre = indices.get(index - 1);
		Node<E> next = indices.get(index);
		Node<E> newNode = new Node<E>(elem, pre, next);
		pre.next = newNode;
		next.prev = newNode;
		size++;
		indices.add(index, newNode);
		return true;
	}

	// adds elem at the head (i.e. it becomes the first element of the list).
	public boolean add(E elem)
	{
		Node<E> newNode = new Node<>(elem);
		indices.add(0, newNode);
		newNode.next = head;
		if (head != null)
		{
			head.prev = newNode;

		} else
		{
			tail = newNode;
		}
		head = newNode;
		size++;
		return true;
	}

	// adds elem as the new last element of the list (i.e. at the tail).
	public boolean append(E elem)
	{
		Node<E> newNode = new Node<>(elem);
		if (tail == null)
		{
			head = newNode;
			tail = head;
		} else
		{
			tail.next = newNode;
			newNode.prev = tail;
			tail = tail.next;
		}
		indices.add(newNode);
		size++;
		return true;
	}

	// returns the object at position index from the head. It uses the index for
	// fast access.
	public E get(int index)
	{
		if (index < 0 || index >= size)
		{
			return null;
		}
		Node<E> curr = indices.get(index);
		return curr.data;
	}

	// returns the object at the head.
	public E getHead()
	{
		if (head != null)
		{
			return head.data;
		}
		return null;
	}

	// returns the object at the tail.
	public E getLast()
	{
		if (tail != null)
		{
			return tail.data;
		}
		return null;
	}

	// returns the list size.
	public int size()
	{
		return size;
	}

	// removes and returns the element at the head.
	public E remove()
	{
		E res = null;
		if (head == null)
		{
			return res;
		} else if (head.next == null)
		{
			res = head.data;
			head = null;
			tail = head;
			size--;
		} else
		{
			res = head.data;
			head = head.next;
			head.prev = null;
			size--;
		}
		indices.remove(0);
		return res;
	}

	// removes and returns the element at the tail.
	public E removeLast()
	{
		E res = null;
		if (tail == null)
		{
			return res;
		} else if (tail.prev == null)
		{
			res = tail.data;
			head = null;
			tail = head;
		} else
		{
			res = tail.data;
			tail = tail.prev;
			tail.next = null;
		}
		indices.remove(--size);
		return res;
	}

	// removes and returns the element at the index index. Use the index for fast
	// access.
	public E removeAt(int index)
	{
		if (index == 0)
		{
			return remove();
		} else if (index == size - 1)
		{
			return removeLast();
		} else
		{
			Node<E> pre = indices.get(index - 1);
			Node<E> curr = indices.get(index);
			Node<E> next = indices.get(index + 1);
			pre.next = next;
			next.prev = pre;
			size--;
			indices.remove(index);
			return curr.data;
		}
	}

	// removes the first occurrence of elem in the list and
	// returns true. Return false if elem was not in the list.
	public boolean remove(E elem)
	{
//		Node<E> newNode = new Node<>(elem);
		int i = 0;
		for (Node<E> curr : indices)
		{
			if (curr.data.equals(elem))
			{
				removeAt(i);
				return true;
			}
			i++;
		}
		return false;
	}

	// presents a string representation of the list.
	@Override
	public String toString()
	{
		String res = "";
		while (head != null)
		{
			res += head.data.toString();
			head = head.next;
		}
		return res;

	}
}

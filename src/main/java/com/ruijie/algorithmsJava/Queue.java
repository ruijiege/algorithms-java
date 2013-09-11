package com.ruijie.algorithmsJava;
/*************************************************************************
 *
 *  A generic queue, implemented using a linked list.
 *  It supports the usual enqueue and dequeue operations, along with methods
 *  for peeking at the top item, testing if the queue is empty, and iterating
 *  through the items in FIFO order.
 *  All queue operations except iteration are constant time.
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private int N; // number of elements on queue
	private Node first; // beginning of queue
	private Node last; // end of queue

	// helper linked list class
	private class Node {
		private Item item;
		private Node next;
	}

	/**
	 * Create an empty queue.
	 */
	public Queue() {
		first = null;
		last = null;
		N = 0;
	}

	/**
	 * Is the queue empty?
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Return the number of items in the queue.
	 */
	public int size() {
		return N;
	}

	/**
	 * Return the item least recently added to the queue.
	 *
	 * @throws java.util.NoSuchElementException
	 *             if queue is empty.
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}

	/**
	 * Add the item to the queue.
	 */
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		N++;
	}

	/**
	 * Remove and return the item on the queue least recently added.
	 *
	 * @throws java.util.NoSuchElementException
	 *             if queue is empty.
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	/**
	 * Return string representation.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	/**
	 * Return an iterator that iterates over the items on the queue in FIFO
	 * order.
	 */
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * A test client.
	 */
	public static void main(String[] args) {
    Queue<String> queue = new Queue<String>();
    queue.enqueue("a");
    queue.enqueue("b");
    queue.enqueue("c");
    queue.enqueue("d");
    queue.enqueue("e");
    queue.enqueue("f");
    queue.enqueue("g");
    queue.dequeue();
    System.out.println(queue.peek());
    System.out.println(queue);
	}
}

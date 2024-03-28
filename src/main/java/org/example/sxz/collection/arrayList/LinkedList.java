package org.example.sxz.collection.arrayList;

import org.example.sxz.collection.Arrays;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

// Deque  = double ended queue ; This interface defines methods to access the elements at both ends of the deque.
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E> {

	/**
	 * Pointer to first node.
	 * Invariant: (first == null && last == null) ||
	 * (first.prev == null && first.item != null)
	 */
	transient Node<E> first;

	/**
	 * Pointer to last node.
	 * Invariant: (first == null && last == null) ||
	 * (last.next == null && last.item != null)
	 */
	transient Node<E> last;

	int size;

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}


	public LinkedList() {
		this.size = 0;
	}

	/**
	 * Constructs a list containing the elements of the specified
	 * collection, in the order they are returned by the collection's
	 * iterator.
	 *
	 * @param c the collection whose elements are to be placed into this list
	 * @throws NullPointerException if the specified collection is null
	 */
	public LinkedList(Collection<? extends E> c) {
		addAll(c);
	}

	/*Deque<E> 的实现*/
	@Override
	public void addFirst(E e) {
		first = new Node<>(null, e, first);
		this.size++;
	}

	@Override
	public void addLast(E e) {
		last = new Node<>(last, e, null);
		this.size++;
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E value = first.item;
		removeFirstElement();
		return value;

	}

	private void removeFirstElement() {
		if (size == 1) {
			first = null;
			last = null;
		} else {
			Node<E> second = first.next;
			second.prev = null;
			first = second;
		}
		size--;
	}

	@Override
	public E removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E value = last.item;
		removeLastElement();
		return value;
	}

	private void removeLastElement() {
		if (size == 1) {
			first = null;
			last = null;
		} else {
			Node<E> secondLast = last.prev;
			secondLast.next = null;
			last = secondLast;
		}
		size--;
	}

	@Override
	public E pollFirst() {
		if (size == 0) {
			return null;
		}
		E value = last.item;
		removeLastElement();
		return value;
	}

	@Override
	public E pollLast() {
		if (size == 0) {
			return null;
		}
		E value = first.item;
		removeFirstElement();
		return value;
	}

	@Override
	public E getFirst() {
		return first.item;
	}

	@Override
	public E getLast() {
		return last.item;
	}

	@Override
	public E peekFirst() {
		return first == null ? null : first.item;
	}

	@Override
	public E peekLast() {
		return last == null ? null : last.item;

	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		Node<E> cur = first;
		while (cur != null) {
			if (cur.item.equals(o)) {
				if (cur.prev == null) {
					removeFirstElement();
					return true;
				} else if (cur.next == null) {
					removeLastElement();
					return true;
				} else {
					Node<E> last = cur.prev;
					Node<E> next = cur.next;
					last.next = next;
					next.prev = last;
					size--;
				}
			}
			cur = cur.next;
		}
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		return false;
	}

	@Override
	public boolean add(E e) {
		return super.add(e);
	}

	@Override
	public boolean offer(E e) {
		return false;
	}

	@Override
	public E remove() {
		return null;
	}

	@Override
	public E poll() {
		return null;
	}

	@Override
	public E element() {
		return null;
	}

	@Override
	public E peek() {
		return null;
	}

	@Override
	public void push(E e) {

	}

	@Override
	public E pop() {
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return super.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return super.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		int collSize = c.size();
		if (collSize != 0) {
			Iterator<E> iterator = (Iterator<E>) c.iterator();
			Node<E> pre = last;
			Node<E> cur;
			while (iterator.hasNext()) {
				cur = new Node<>(pre, iterator.next(), null);
				if (pre == null) {
					first = cur;
				} else {
					pre.next = cur;
				}
				pre = cur;
				this.size++;
			}
			// add完毕后把结尾的值赋给last
			last = new Node<>(pre.prev, pre.item, null);
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return super.removeAll(c);
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		return super.removeIf(filter);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return super.retainAll(c);
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
	}

	@Override
	public void sort(Comparator<? super E> c) {
		// toArray -> sort array -> toLinkedList  (O(n) + O(nlogn) + O(n))
		// forEach -> sort array -> toLinkedList  (O(n) + O(nlogn) + O(n))
		if (size > 1) {
			Integer[] sortedArray = new Integer[size];
			Node<E> cur = first;
			int index = -1;
			while (cur != null) {
				int partitionIndex =
						Arrays.binarySearchPartitionIndex(sortedArray, 0, Math.max(index, 0), (Integer) cur.item);
				System.arraycopy(sortedArray, 0, sortedArray, partitionIndex + 1, index - partitionIndex + 1);
				sortedArray[partitionIndex] = (Integer) cur.item;
				index++;
				cur = cur.next;
			}
			//根据sortedArray依次赋值双向node； 需要更改cur.item、cur.prev、prev.next
			Node<E> prev = null;
			Node<E> head = first;
			for (int i = 0; i < size; i++) {
				first.item = (E) sortedArray[i];
				first.prev = prev;
				if (prev != null) {
					prev.next = first;
				}
				if (i == size - 1) {
					last = first;
				}
				first = first.next;
			}
			first = head;
		}
	}


	@Override
	public E get(int index) {
		checkRangeLegal(index);
		Node<E> cur;
		cur = getNode(index);
		return cur.item;
	}

	private Node<E> getNode(int index) {
		Node<E> cur;
		if (index < (size / 2)) {
			// 从前往后数
			cur = first;
			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}
		} else {
			// 从后往前
			cur = last;
			for (int i = 0; i < size - index - 1; i++) {
				cur = cur.prev;
			}
		}
		return cur;
	}

	private void checkRangeLegal(int index) {
		if (index + 1 > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}


	@Override
	public void add(int index, E element) {
		super.add(index, element);
	}

	@Override
	public E remove(int index) {
		return super.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return super.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return super.lastIndexOf(o);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return super.addAll(index, c);
	}

	@Override
	public Iterator<E> iterator() {
		return super.iterator();
	}

	@Override
	public Iterator<E> descendingIterator() {
		// todo
		return null;
	}

	@Override
	public int size() {
		return size;
	}


	@Override
	public void forEach(Consumer<? super E> action) {

		// todo
		super.forEach(action);
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}

	@Override
	public Object[] toArray() {
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return super.toArray(a);
	}

	@Override
	public ListIterator<E> listIterator() {
		return super.listIterator();
	}

	/* Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.
	The specified index indicates the first element that would be returned by an initial call to next.
	An initial call to previous would return the element with the specified index minus one.*/
	@Override
	public ListIterator<E> listIterator(int index) {
		return new ListItr(index);
	}

	public class ListItr implements ListIterator<E> {
		private Node<E> lastReturned;
		private Node<E> next;
		private int nextIndex;

		public ListItr(int index) {
			next = (index == size) ? null : getNode(index);
			nextIndex = index;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		@Override
		public String toString() {
			return super.toString();
		}

		@Override
		protected void finalize() throws Throwable {
			super.finalize();
		}

		@Override
		public void forEachRemaining(Consumer<? super E> action) {
			ListIterator.super.forEachRemaining(action);
		}

		@Override
		public boolean hasNext() {
			return next == null;
		}

		@Override
		public E next() {
			return null;
		}

		@Override
		public boolean hasPrevious() {
			return false;
		}

		@Override
		public E previous() {
			return null;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}

		@Override
		public void remove() {

		}

		@Override
		public void set(E e) {

		}

		@Override
		public void add(E e) {

		}
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return super.subList(fromIndex, toIndex);
	}

	@Override
	public Spliterator<E> spliterator() {
		return super.spliterator();
	}

	@Override
	public Stream<E> stream() {
		//todo
		return super.stream();
	}

	@Override
	public Stream<E> parallelStream() {
		return super.parallelStream();
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
}

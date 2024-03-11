package org.example.sxz.collection.arrayList;


import org.example.sxz.collection.Arrays;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


/**
 * 自定义实现array list
 *
 * @see java.util.ArrayList
 */
public class ArrayList<E> extends AbstractList<E> {
	private final static int defaultLength = 10;

	private Object[] objects;

	private int allSize = 0;
	private int usedSize = 0;

	public ArrayList() {
		objects = new Object[0];
	}

	public ArrayList(Collection<? extends E> c) {
		int objectLength = c.size();
		objects = new Object[objectLength];
		for (E element : c) {
			objects[usedSize++] = element;
		}
		allSize = objectLength;
	}

	public ArrayList(int length) {
		objects = new Object[length];
		allSize = length;
	}


	@Override
	public E get(int index) {
		return (E) this.objects[index];
	}

	@Override
	public E set(int index, E element) {
		return super.set(index, element);
	}

	@Override
	public void add(int index, E element) {
		ensureCapacity(this.usedSize + 1);
		objects[index] = element;
		this.usedSize += 1;
	}

	@Override
	public E remove(int index) {
		return super.remove(index);
	}

	@Override
	public boolean add(Object o) {
		ensureCapacity(this.usedSize + 1);
		objects[this.usedSize++] = o;
		return true;
	}


	private void ensureCapacity(int i) {
		if (allSize < usedSize + i) {
			if (allSize == 0) {
				allSize = defaultLength;
			} else {
				allSize = (int) (allSize * 1.5);
			}
			objects = Arrays.copyOf(objects, allSize);
		}
	}

	@Override
	public int size() {
		return this.usedSize;
	}

	@Override
	public String toString() {
		String output = "";
		for (Object element : objects) {
			output += element.toString() + ";";
		}
		return output;
	}

	/*
	Retains only the elements in this list that are contained in the specified collection (optional operation).
	 In other words, removes from this list all of its elements that are not contained in the specified collection.
	*/
	@Override
	public boolean retainAll(Collection c) {
		return super.retainAll(c);
	}

	// Removes from this list all of its elements that are contained in the specified collection (optional operation).
	@Override
	public boolean removeAll(Collection c) {
		if (c.size() == 0) {
			return true;
		} else {
			boolean flag = true;
			for (Object element : objects) {
				if (c.contains(element)) {
					flag = flag && remove(element);
				}
			}
			return flag;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
		if (c.size() == 0) {
			return modified;
		} else {
			for (E element : c) {
				modified = this.add(element) || modified;
			}
		}
		return modified;
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		return super.removeIf(filter);
	}

	@Override
	public boolean containsAll(Collection c) {
		return super.containsAll(c);
	}

	@Override
	public boolean remove(Object o) {
		boolean changed = false;
		for (int i = 0; i < objects.length; i++) {
			if (objects[i].equals(o)) {
				changed = true;
				int numLength = this.allSize - i - 1;
				if (numLength > 0) {
					System.arraycopy(objects, i + 1, objects, i, numLength);
				}
			}
		}
		return changed;
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		super.replaceAll(operator);
	}


	@Override
	public void sort(Comparator<? super E> c) {
		Arrays.binarySort(objects, c);

	}

	@Override
	public Object[] toArray(Object[] a) {
		return super.toArray(a);
	}

	@Override
	public Object[] toArray() {
		return objects;
	}

	@Override
	public boolean contains(Object o) {
		return super.contains(o);
	}

	@Override
	public boolean isEmpty() {
		return usedSize == 0;
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		super.removeRange(fromIndex, toIndex);
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	// todo
	@Override
	public Stream<E> stream() {
		return super.stream();
	}

	@Override
	public Stream<E> parallelStream() {
		return super.parallelStream();
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		return super.subList(fromIndex, toIndex);
	}

	@Override
	public Iterator iterator() {
		return super.iterator();
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		super.forEach(action);
	}

	// todo
	@Override
	public ListIterator<E> listIterator() {
		return super.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return super.listIterator(index);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		return true;
	}

	@Override
	public void clear() {
		objects = new Object[0];
		this.usedSize = 0;
		this.allSize = 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = objects.length - 1; i >= 0; i--) {
			if (objects[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}


}

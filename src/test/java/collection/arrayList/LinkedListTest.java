package collection.arrayList;

import org.example.sxz.collection.arrayList.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class LinkedListTest {
	java.util.LinkedList standardLinkedList;
	LinkedList linkedList;


	@Before
	public void init() {
		List<String> a = Arrays.asList("a", "b", "c", "a");
		standardLinkedList = new java.util.LinkedList(a);
		linkedList = new LinkedList(a);
	}

	@Test
	public void testGet() {
		java.util.LinkedList standardEmptyLinkedList = new java.util.LinkedList();
		Assert.assertEquals(standardEmptyLinkedList.size(), 0);
		Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
			standardEmptyLinkedList.get(0);
		});
		Assert.assertEquals(standardLinkedList.size(), 4);
		Assert.assertEquals(standardLinkedList.get(3), "a");
		Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
			standardEmptyLinkedList.get(6);
		});

		LinkedList empltyLinkedList = new LinkedList();
		Assert.assertEquals(empltyLinkedList.size(), 0);
		Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
			empltyLinkedList.get(0);
		});
		Assert.assertEquals(linkedList.size(), 4);
		Assert.assertEquals(linkedList.get(3), "a");
		Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
			linkedList.get(6);
		});
	}


	@Test
	public void testAddFirst() {
		standardLinkedList.addFirst("first");
		linkedList.addFirst("first");
		Assert.assertEquals(standardLinkedList.getFirst(), "first");
		Assert.assertEquals(standardLinkedList.get(1), "a");
		Assert.assertEquals(linkedList.getFirst(), "first");
		Assert.assertEquals(linkedList.get(1), "a");
	}

	@Test
	public void testAddLast() {
		standardLinkedList.addLast("last");
		linkedList.addLast("last");
		Assert.assertEquals(standardLinkedList.getLast(), "last");
		Assert.assertEquals(linkedList.getLast(), "last");
	}

	@Test
	public void testOffer() {
		standardLinkedList.offerFirst("first");
		linkedList.offerFirst("first");
		Assert.assertEquals(standardLinkedList.getFirst(), "first");
		Assert.assertEquals(linkedList.getFirst(), "first");
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(standardLinkedList.removeFirst(), "a");
		Assert.assertEquals(standardLinkedList.removeLast(), "a");
		Assert.assertEquals(standardLinkedList.getFirst(), "b");
		Assert.assertEquals(standardLinkedList.getLast(), "c");
		standardLinkedList = new java.util.LinkedList<>(Arrays.asList("a", "b"));
		Assert.assertEquals(standardLinkedList.removeFirst(), "a");
		Assert.assertEquals(standardLinkedList.getFirst(), "b");
		Assert.assertEquals(standardLinkedList.getLast(), "b");
		Assert.assertEquals(standardLinkedList.size(), 1);
		Assert.assertEquals(linkedList.removeFirst(), "a");
		Assert.assertEquals(linkedList.removeLast(), "a");
		Assert.assertEquals(linkedList.getFirst(), "b");
		Assert.assertEquals(linkedList.getLast(), "c");
		linkedList = new LinkedList(Arrays.asList("a", "b"));
		Assert.assertEquals(linkedList.removeFirst(), "a");
		Assert.assertEquals(linkedList.getFirst(), "b");
		Assert.assertEquals(linkedList.getLast(), "b");
		Assert.assertEquals(linkedList.size(), 1);
	}

	@Test
	public void testPoll() {
		Assert.assertEquals(standardLinkedList.pollFirst(), "a");
		Assert.assertEquals(standardLinkedList.pollLast(), "a");
		Assert.assertEquals(standardLinkedList.size(), 2);
		standardLinkedList = new java.util.LinkedList();
		Assert.assertEquals(standardLinkedList.pollLast(), null);

		Assert.assertEquals(linkedList.pollFirst(), "a");
		Assert.assertEquals(linkedList.pollLast(), "a");
		Assert.assertEquals(linkedList.size(), 2);
		linkedList = new LinkedList();
		Assert.assertEquals(linkedList.pollLast(), null);
	}


	@Test
	public void testRemoveOccurrence() {
		standardLinkedList.removeFirstOccurrence("a");
		Assert.assertEquals(standardLinkedList.getFirst(), "b");
		Assert.assertEquals(standardLinkedList.removeFirstOccurrence("abc"), false);
		Assert.assertEquals(standardLinkedList.size(), 3);
		linkedList.removeFirstOccurrence("a");
		Assert.assertEquals(linkedList.getFirst(), "b");
		Assert.assertEquals(linkedList.removeFirstOccurrence("abc"), false);
		Assert.assertEquals(linkedList.size(), 3);
	}


	@Test
	public void testSort() {
		List<Integer> b = Arrays.asList(5, 3, 7, 9, 1);
		standardLinkedList = new java.util.LinkedList(b);
		linkedList = new LinkedList(b);


		Comparator<Integer> comparator = (Integer o1, Integer o2) -> {
			if (o1 > o2) {
				return o1;
			} else if (o1.equals(o2)) {
				return 0;
			} else {
				return -o2;
			}
		};

		standardLinkedList.sort(comparator);
		linkedList.sort(comparator);
		Assert.assertEquals(standardLinkedList.getFirst(), 1);
		Assert.assertEquals(linkedList.getFirst(), 1);
		Assert.assertEquals(standardLinkedList.getLast(), 9);
		Assert.assertEquals(linkedList.getLast(), 9);

	}


	@Test
	public void testListItr() {




	}

}

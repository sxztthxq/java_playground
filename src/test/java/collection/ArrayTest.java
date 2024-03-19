package collection;

import org.example.sxz.collection.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

public class ArrayTest {

	Integer[] objects;
	Integer[] sorted_objects;

	Comparator<? super Integer> comparator;

	@Before
	public void init() {
		objects = new Integer[]{3, 2, 1, 4};

		sorted_objects = new Integer[]{1, 2, 3, 4};


		comparator = (Comparator<Integer>) (o1, o2) -> {
			if (o1 > o2) {
				return -o1;
			} else if (o1.equals(o2)) {
				return 0;
			} else {
				return o2;
			}
		};
	}

	@Test
	public void TestBinarySort() {
		Arrays.binarySort(objects, comparator);
		System.out.println(objects[0]);
		System.out.println(objects[1]);
		System.out.println(objects[2]);
		System.out.println(objects[3]);
	}

	@Test
	public void TestBubbleSort() {
		Arrays.bubbleSort(objects, comparator);
		Assert.assertEquals(objects, sorted_objects);
	}

	@Test
	public void TestSelectionSort() {
		Arrays.selectionSort(objects, comparator);
		Assert.assertEquals(objects, sorted_objects);
	}

	@Test
	public void TestQuickSort() {
		Arrays.quickSort(objects, comparator);
		Assert.assertEquals(objects, sorted_objects);
	}

	@Test
	public void TestCopyOf() {
		Integer[] expected = new Integer[10];
		expected[0] = 3;
		expected[1] = 2;
		expected[2] = 1;
		expected[3] = 4;
		Assert.assertEquals(java.util.Arrays.copyOf(objects, 10), expected);
		Assert.assertEquals(Arrays.copyOf(objects, 10), expected);
	}

	@Test
	public void TestCopyOfRange() {
		Integer[] expected = new Integer[2];
		expected[0] = 2;
		expected[1] = 1;
		Assert.assertEquals(java.util.Arrays.copyOfRange(objects, 1, 3), expected);
		Assert.assertEquals(Arrays.copyOfRange(objects, 1, 3), expected);
	}


	@Test
	public void TestSystemArrayCopy() {
		Integer[] expected = new Integer[10];
		System.arraycopy(objects, 0, expected, 0, 4);
		System.out.println(expected[0]);
	}

}

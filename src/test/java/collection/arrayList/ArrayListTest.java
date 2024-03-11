package collection.arrayList;

import org.example.sxz.collection.arrayList.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ArrayListTest {
	java.util.ArrayList standardArrayList;
	ArrayList myArrayList;

	@Before
	public void init() {
		List<String> a = Arrays.asList("a", "b", "c", "a");
		standardArrayList = new java.util.ArrayList(a);
		myArrayList = new ArrayList(a);
	}

	@Test
	public void testConstructor() {
		standardArrayList = new java.util.ArrayList();
		myArrayList = new ArrayList();
		Assert.assertEquals(standardArrayList.size(), myArrayList.size());
	}

	@Test
	public void testConstructorWithCollection() {
		List<String> a = Arrays.asList("a", "b", "c");
		standardArrayList = new java.util.ArrayList(a);
		myArrayList = new ArrayList(a);
		Assert.assertEquals(standardArrayList.size(), myArrayList.size());
		for (int i = 0; i < a.size(); i++) {
			Assert.assertEquals(standardArrayList.get(i), myArrayList.get(i));
		}
		List<String> b = Collections.emptyList();
		standardArrayList = new java.util.ArrayList(b);
		myArrayList = new ArrayList(b);
		Assert.assertEquals(standardArrayList.size(), myArrayList.size());
		for (int i = 0; i < b.size(); i++) {
			Assert.assertEquals(standardArrayList.get(i), myArrayList.get(i));
		}
	}

	@Test
	public void testSize() {
		Assert.assertEquals(myArrayList.size(), 4);
	}

	@Test
	public void testAdd() {
		myArrayList = new ArrayList(Arrays.asList("a", "b", "c"));
		myArrayList.add("String");
		Assert.assertEquals(myArrayList.get(3), "String");
		myArrayList.add(0, "test");
		Assert.assertEquals(myArrayList.get(0), "test");
		Assert.assertEquals(myArrayList.size(), 5);
	}

	@Test
	public void testAddAll() {
		myArrayList = new ArrayList(Arrays.asList("a", "b", "c"));
		myArrayList.addAll(Arrays.asList("d", "e", "f"));
		Assert.assertEquals(myArrayList.get(3), "d");
		Assert.assertEquals(myArrayList.get(4), "e");
		Assert.assertEquals(myArrayList.get(5), "f");
		myArrayList.addAll(Arrays.asList("d", "e", "f"));


	}

	@Test
	public void testRemove() {
		myArrayList = new ArrayList(Arrays.asList("a", "b", "c"));
		myArrayList.remove("a");
		Assert.assertEquals(myArrayList.get(0), "b");
		Assert.assertEquals(myArrayList.get(1), "c");
		List<String> a = Arrays.asList("a", "b", "c");
		standardArrayList = new java.util.ArrayList(a);
		standardArrayList.remove("a");
		Assert.assertEquals(standardArrayList.get(0), "b");
		Assert.assertEquals(standardArrayList.get(1), "c");
	}

	@Test
	public void testIndexOf() {
		Assert.assertEquals(myArrayList.indexOf("a"), 0);
		Assert.assertEquals(myArrayList.indexOf("e"), -1);
	}

	@Test
	public void testLastIndexOf() {
		Assert.assertEquals(myArrayList.lastIndexOf("a"), 3);
		Assert.assertEquals(myArrayList.indexOf("e"), -1);

	}

	@Test
	public void testClear() {
		this.myArrayList.clear();
		Assert.assertEquals(this.myArrayList.size(), 0);
		this.standardArrayList.clear();
		Assert.assertEquals(this.standardArrayList.size(), 0);
	}

	@Test
	public void testCompare() {
		this.standardArrayList = new java.util.ArrayList<>(Arrays.asList(3, 2, 1));
		this.myArrayList = new ArrayList(Arrays.asList(3, 2, 1));
		Comparator<? super Integer> comparator = (Comparator<Integer>) (o1, o2) -> {
			if (o1 < o2) {
				return -o1;
			} else if (o1.equals(o2)) {
				return 0;
			} else {
				return o2;
			}
		};
		this.standardArrayList.sort(comparator);
		this.myArrayList.sort(comparator);
		Assert.assertEquals(this.standardArrayList.get(0), 1);
		Assert.assertEquals(this.myArrayList.get(0), 1);


	}
}

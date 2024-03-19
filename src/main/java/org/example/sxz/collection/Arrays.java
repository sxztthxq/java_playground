package org.example.sxz.collection;

import java.util.Comparator;

import org.apache.commons.lang3.NotImplementedException;

public class Arrays {

	public static Object[] copyOf(Object[] objects, int size) {
		Object[] copy = new Object[size];
		for (int i = 0; i < objects.length; i++) {
			copy[i] = objects[i];
		}
		return copy;
	}

	public static <T> T[] copyOfRange(T[] original, int from, int to) {
		T[] copy = (T[]) new Object[to - from];
		int index = 0;
		for (int i = from; i < to; i++) {
			copy[index++] = original[i];
		}
		return copy;
	}

	/*
	从指定位置开始，将指定源数组中的一个数组复制到目标数组的指定位置。
	从 src 所引用的源数组到 dest 所引用的目标数组的数组组件的子序列被复制。
	复制的组件数等于长度参数。源数组中位于 srcPos 至 srcPos+length-1 位置的组件将分别复制到目标数组的 destPos 至 destPos+length-1 位置。
	如果 src 和 dest 参数指向的是同一个数组对象，那么复制过程就如同先将 srcPos 至 srcPos+length-1
	位置上的分量复制到一个有长度分量的临时数组，然后将临时数组的内容复制到目标数组的 destPos 至 destPos+length-1 位置。
	*/
	public static void SystemArraycopy(Object src, int srcPos,
									   Object dest, int destPos,
									   int length) {
		throw new NotImplementedException();
	}


	//排序算法
	// todo
	public static <E> void binarySort(Object[] objects, Comparator<? super E> c) {
		if (objects.length == 0 || objects.length == 1) {
			return;
		} else {
			// 排序第一、二个元素
			if (c.compare((E) objects[0], (E) objects[1]) < 0) {
				Object temp = objects[1];
				objects[1] = objects[0];
				objects[0] = temp;
			}
		}
	}

	public static <E> void quickSort(Object[] objects, Comparator<? super E> c) {
		rangeQuickSort(objects, 0, objects.length, c);
	}

	private static <E> void rangeQuickSort(Object[] objects, int start, int end, Comparator<? super E> c) {
		if (end > start) {
			int partitionIndex = getPartitionIndex(objects, start, end, c);
			rangeQuickSort(objects, start, partitionIndex, c);
			rangeQuickSort(objects, partitionIndex + 1, end, c);
		}
	}

	private static <E> int getPartitionIndex(Object[] objects, int start, int end, Comparator<? super E> c) {
		// 以当前值为基准值
		Object partitionValue = objects[start];
		int curIndex = start;
		for (int i = start + 1; i < end; i++) {
			// 遍历当前值之后的所有值，如果比基准值小，则和curIndex的值对换，然后curIndex+1；这样能让所有比基准值小的都在左边，大的换到右边去
			if (c.compare((E) objects[i], (E) partitionValue) > 0) {
				Object temp = objects[i];
				objects[i] = objects[curIndex];
				objects[curIndex] = temp;
				curIndex++;
			}
		}
		// 最后curIndex的位置的值即为基准值应该处于的位置
		objects[curIndex] = partitionValue;
		return curIndex;
	}

	public static <E> void selectionSort(Object[] objects, Comparator<? super E> c) {
		for (int i = 0; i < objects.length; i++) {
			Object min = objects[i];
			for (int j = i + 1; j < objects.length; j++) {
				if (c.compare((E) min, (E) objects[j]) < 0) {
					min = objects[j];
					objects[j] = objects[i];
					objects[i] = min;
				}
			}
		}
	}

	public static <E> void bubbleSort(Object[] objects, Comparator<? super E> c) {
		for (int i = 0; i < objects.length; i++) {
			for (int j = i + 1; j < objects.length; j++) {
				if (c.compare((E) objects[i], (E) objects[j]) < 0) {
					Object temp = objects[i];
					objects[i] = objects[j];
					objects[j] = temp;
				}
			}
		}
	}

}

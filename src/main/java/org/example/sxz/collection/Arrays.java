package org.example.sxz.collection;

import java.util.Comparator;

public class Arrays {


	// todo
	public static Object[] copyOf(Object[] objects, int size) {
		return objects;
	}

	////排序算法
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
			// 从第三个元素开始进行二分排序
			for (int i = 2; i < objects.length; i++) {
				Object cur = objects[i];
				int start = 0;
				int end = i - 1;
				while (end > start) {
					int middle = (start + end) / 2;
					if (c.compare((E) objects[middle], (E) objects[i]) < 0) {
						end = middle;
					} else {
						start = middle + 1;
					}
				}
				for (int j = i - 1; j >= start; j--) {
					//从i-1到left依次向后移动一位,等待temp值插入
					objects[j + 1] = objects[j];
				}
				objects[start] = cur;


//				int copyLength = i - start;
//				// todo:自定义实现
//				System.arraycopy(objects, start, objects, start + 1, copyLength);
//				objects[start] = cur;
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

package com.digiteched.javadsa;

import com.digiteched.javadsa.interfaces.IMinHeap;

public class SearchAndSort {
    // "Add an additional static method called `heapSort` to the `SearchAndSort`
    // class." done? you didn't say what you wanted it to do...
    public static <T extends Comparable<T>> void heapSort(T[] items) {
        IMinHeap<T> minHeap = new LinkedMinHeap<T>();

        for (T item : items) {
            minHeap.add(item);
            System.out.println(minHeap);
        }

        for (int i = 0; i < items.length; i++) {
            items[i] = minHeap.removeMin();
            System.out.println(minHeap);
        }

    }
}

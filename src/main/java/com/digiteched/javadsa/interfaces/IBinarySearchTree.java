package com.digiteched.javadsa.interfaces;

public interface IBinarySearchTree<T extends Comparable<T>> extends Iterable<T> {
    int size();

    boolean isEmpty();
    
    void add(T element);
    
    void remove(T target);

    // void removeAll(T targetElement);

    T removeMin();

    T removeMax();

    T getRoot();

    boolean contains(T target);
} 

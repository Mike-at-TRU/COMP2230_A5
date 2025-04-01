package com.digiteched.javadsa.interfaces;

public interface IMinHeap<T> {
    int size();

    boolean isEmpty();

    void add(T element);

    T removeMin();

    T getMin();
}
package com.digiteched.javadsa.interfaces;

public interface  IQueue<T> {
    void enqueue(T item);

    T dequeue();

    int size();
}

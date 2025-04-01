package com.digiteched.javadsa.interfaces;

public interface IDequeue<T> {
    void addFirst(T element);

    T getFirst();

    T removeFirst();

    void addLast(T element);

    T getLast();

    T removeLast();

    int size();
}

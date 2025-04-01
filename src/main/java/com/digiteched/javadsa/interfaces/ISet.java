package com.digiteched.javadsa.interfaces;

public interface ISet<T> {
    int size();

    // AKA find
    boolean has(T item);

    void add(T item);

    void remove(T item);
}

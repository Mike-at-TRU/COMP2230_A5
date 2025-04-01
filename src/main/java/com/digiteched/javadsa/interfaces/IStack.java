package com.digiteched.javadsa.interfaces;

public interface IStack<T> {
    void push(T item);

    T pop ();

    int size();
}

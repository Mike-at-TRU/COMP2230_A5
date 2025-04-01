package com.digiteched.javadsa.utils;

import java.lang.reflect.Array;

public class Factory<T> {
    Class<T> t;

    public Class<T> type() {
        return t;
    }

    /**
     * Constructor - creates a factory for creating objects and
     * arrays of type t(=T)
     * 
     * @param t0
     */
    public Factory(Class<T> t0) {
        t = t0;
    }

    /**
     * Allocate a new array of objects of type T.
     * 
     * @param n the size of the array to allocate
     * @return the array allocated
     */
    @SuppressWarnings({ "unchecked" })
    public T[] newArray(int n) {
        return (T[]) Array.newInstance(t, n);
    }

    @SuppressWarnings("deprecation")
    public T newInstance() {
        T x;
        try {
            x = t.newInstance();
        } catch (Exception e) {
            x = null;
        }

        return x;
    }
}

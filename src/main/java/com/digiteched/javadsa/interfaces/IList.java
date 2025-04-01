package com.digiteched.javadsa.interfaces;

public interface IList<T> extends Iterable<T> {
    /**
     * 
     * @param index      The index to update
     * @param newElement The element that will now be stored at this index
     *                   Note that this operation overwrites an existing element.
     */
    void set(int index, T newElement);

    /**
     * 
     * @param element the element to insert at the end of the list.
     */
    void add(T element);

    /**
     * 
     * @param index   the index of the newly added element
     * @param element the element to insert at the given index
     *                Note that the element will be inserted after the element with
     *                index `index-1` if i>0, or at the beginning of the list if
     *                i is 0.
     */
    void add(int index, T element);

    /**
     * 
     * @param index the index whose element will be removed from the list
     */
    T remove(int index);

    T remove(T item);

    /**
     * 
     * @param index The index of the element to get.
     * @return The element at the given index
     */
    T get(int index);

    /**
     * 
     * @return The size (i.e., the number of elements) of the list
     */
    int size();

    /**
     * 
     * @param element
     * @return true if the element is in the list
     */
    boolean has(T element);
}

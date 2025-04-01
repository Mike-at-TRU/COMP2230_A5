package com.digiteched.javadsa;


import com.digiteched.javadsa.interfaces.IMinHeap;

public class LinkedMinHeap<T extends Comparable<T>> implements IMinHeap<T> {
    class LinkedNode implements Comparable<LinkedNode> {
        T data;

        public LinkedNode(T data) {
            this.data = data;
        }

        LinkedNode left;

        LinkedNode right;



        @Override
        public int compareTo(LinkedNode that) {
            return this.data.compareTo(that.data);
        }
    }
    private int count = 0;
    private LinkedNode root;

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
     return count == 0;
    }

    @Override
    public void add(T element) {

        LinkedNode nodeToAdd = new LinkedNode(element);
        if(isEmpty()){
            root = nodeToAdd;
            count++;
            return;
        }
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T removeMin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMin'");
    }

    @Override
    public T getMin() {
      return root.data;
    }

}

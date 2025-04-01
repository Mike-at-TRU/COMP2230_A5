package com.digiteched.javadsa;


import com.digiteched.javadsa.interfaces.IMinHeap;

public class LinkedMinHeap<T extends Comparable<T>> implements IMinHeap<T> {
    class LinkedNode implements Comparable<LinkedNode> {
        T data;

        public LinkedNode(T data) {
            this.data = data;
        }

        LinkedNode parent;

        LinkedNode left;

        LinkedNode right;



        @Override
        public int compareTo(LinkedNode that) {
            return this.data.compareTo(that.data);
        }
    }
    private int count = 0;
    private LinkedNode root;
    private LinkedNode lastNodeAdded;

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
            lastNodeAdded = nodeToAdd;
            count++;
            return; //no need to bubble up
        }
        LinkedNode nodeToAddTo = nodeToAddTo();
        if (nodeToAddTo.left == null){
            nodeToAddTo.left = nodeToAdd;
        }
        else if(nodeToAddTo.right == null){
            nodeToAddTo.right = nodeToAdd;
        } else {
            throw new UnsupportedOperationException("How get here?");
        }
        nodeToAdd.parent = nodeToAddTo;
        count++;
        bubbleUp(nodeToAddTo);
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

    private LinkedNode nodeToAddTo(){
        return nodeToAddTo(lastNodeAdded);
    }

    private LinkedNode nodeToAddTo(LinkedNode lastNodeChecked){
        if(lastNodeChecked == root){
            //we have either gone all the way up and need to go left, or we started here
            if(lastNodeChecked.left == null || lastNodeChecked.right == null){
                return root; //we are adding to the root
            }
        }
        LinkedNode parent = lastNodeChecked.parent;
        if(parent.right == lastNodeChecked){
           return nodeToAddTo(parent);
    }
throw new UnsupportedOperationException("I have no idea what I'm doing");
}
private void bubbleUp(LinkedNode node){
        if(node == root){
            return;
        }
        if(node.parent.compareTo(node) < 0 || node.parent.compareTo(node) > 0){
       //todo make sure this is the right comparison
            //todo swap children and make the node's parent it's grandparent and the old parents parent to be itself

            throw new UnsupportedOperationException("Bubble up no workie yet");
            //why am I getting an assertion error and not this one?

        }

}
}

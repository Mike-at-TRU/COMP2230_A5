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
        bubbleUp(nodeToAdd);
    }

    @Override
    public T removeMin() {
        // TODO Auto-generated method stub
        T temp = root.data;
        lastNodeAdded.left = root.left;
        lastNodeAdded.right = root.right;
        root = lastNodeAdded;
        if(root.left != null){
            root.left.parent = root;
        }
        if(root.right != null){
            root.right.parent = root;
        }
        bubbleDown(root);
        return temp;
    }

    private void bubbleDown(LinkedNode node) {
        if(node.left == null || node.right == null){ // we know we are at the bottom, either one child left or no child
            if(node.right != null){ //this would mean left is null and I fucked something up
                throw new UnsupportedOperationException("I think I fucked up");
            }

            if(node.left != null){
                if(node.compareTo(node.left) > 0){
                    // we know left shouldn't have children since we only have one
                    LinkedNode newParent = node.left;
                    node.left = null;
                    newParent.left = node;
                   newParent.parent = node.parent;
                   node.parent = newParent;

                   if(root == node){
                       root = newParent;
                   }

                }
                return;
            }
            return; // if we get here they both were null and we are a leaf
        }
        if(node.compareTo(node.left) < 0 || node.compareTo(node.right) < 0){ // assuming I got the < right we know a child is lower
            if(node.left.compareTo(node.right) < 0){ //the right is smaller ? why didn't I make another custom method for this?
                LinkedNode temp = node.right;

            }
            throw new UnsupportedOperationException();
        }
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
            //we know we were brought all the way up time to go all the way down
            return asFarLeft(root);
        }
        LinkedNode parent = lastNodeChecked.parent;
        if(parent.left == lastNodeChecked){
           return nodeToAddTo(parent);
    }
return asFarLeft(parent.right);
}
private void bubbleUp(LinkedNode node){
        if(node == root){
            return;
        }
        if(node.parent.compareTo(node) > 0 ){ //parent bigger than child

            LinkedNode tempOldParent = node.parent;
            if(tempOldParent.left == node){
                //we know we were the left child
                tempOldParent.left = node.left;
                if(tempOldParent.left != null){
                    tempOldParent.left.parent = tempOldParent;
                }
                LinkedNode tempChild = tempOldParent.right;
                if(tempChild != null){
                    tempChild.parent = node;
                }
                node.parent = tempOldParent.parent;
                tempOldParent.parent = node;
                node.left = tempOldParent;
                if(tempOldParent == root){
                    root = node;
                }
                return;
            }
            //we know we were the right child
            tempOldParent.right = node.right;
            if(tempOldParent.right != null){
                tempOldParent.right.parent = tempOldParent;
            }
            LinkedNode tempChild = tempOldParent.left;
            if(tempChild != null){
            tempChild.parent = node;}
            node.parent = tempOldParent.parent;
            tempOldParent.parent = node;
            node.right = tempOldParent;
            if(tempOldParent == root){
                root = node;
            }


        }

}

private LinkedNode asFarLeft(LinkedNode node){
        if(node.left == null){
            return node;
        }
        return asFarLeft(node.left);
}
}

package com.digiteched.javadsa;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.digiteched.javadsa.LinkedBinarySearchTree.LinkedBinaryNode;
import com.digiteched.javadsa.interfaces.IMinHeap;

public class LinkedMinHeap<T extends Comparable<T>> implements IMinHeap<T> {
    // I don't know why this works but it does
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
        if (isEmpty()) {
            root = nodeToAdd;
            lastNodeAdded = nodeToAdd;
            count++;
            return; // no need to bubble up
        }
        LinkedNode nodeToAddTo = nodeToAddTo();
        if (nodeToAddTo.left == null) {
            nodeToAddTo.left = nodeToAdd;
        } else if (nodeToAddTo.right == null) {
            nodeToAddTo.right = nodeToAdd;
        } else {
            throw new UnsupportedOperationException("How get here?");
        }
        nodeToAdd.parent = nodeToAddTo;
        count++;
        lastNodeAdded = nodeToAdd;
        bubbleUp();
    }

    @Override
    public T removeMin() {
        T temp = root.data;
        if (count == 1) {
            root = null;
            count--;
            lastNodeAdded = null;
            return temp;
        }
        root.data = lastNodeAdded.data;

        plinkoDown();
        count--;
        LinkedNode oldLastNode = lastNodeAdded;
        lastNodeAdded = getNewLastNode();

        if (oldLastNode.parent.right == oldLastNode) {
            oldLastNode.parent.right = null;
        } else {
            oldLastNode.parent.left = null;
        }

        return temp; // expects 1 got 3
    }

    private LinkedNode getNewLastNode() {
        LinkedNode result = lastNodeAdded;
        while (result != root && result.parent.left == result) {
            result = result.parent;
        }
        if (result != root) {
            result = result.parent.left;
        }
        while (result.right != null) {
            result = result.right;
        }

        return result;

    }

    private void plinkoDown() {
        LinkedNode node = root;
        LinkedNode next = nextToComparePlinko(node);

        T temp = node.data;

        while (next != null && next.data.compareTo(temp) < 0) {
            node.data = next.data;
            node = next;

            next = nextToComparePlinko(node);
        }

        node.data = temp;

    }

    private LinkedNode nextToComparePlinko(LinkedNode node) {
        LinkedNode left = node.left;
        LinkedNode right = node.right;

        if (left == null && right == null) {
            return null;
        } else if (right == null) {
            return left;
        } else if (left.data.compareTo(right.data) < 0) {
            return left;
        } else {
            return right;
        }
    }

    private boolean aChildIsSmaller(LinkedNode node) {
        return node.compareTo(node.left) > 0 || node.compareTo(node.right) > 0;
    }

    @Override
    public T getMin() {
        if (root == null) {
            throw new UnsupportedOperationException(
                    "How am I supposed to get nothing? come on add something to me first");
        }
        return root.data;
    }

    private LinkedNode nodeToAddTo() {
        LinkedNode node = lastNodeAdded;
        if (node == root) {
            return root;
        }

        while (node != root && node.parent.left != node) {
            node = node.parent;
        }
        if (node != root) {
            if (node.parent.right == null) {
                return node.parent;
            } else {
                node = asFarLeft(node.parent.right);
            }
        } else { // full tree
            node = asFarLeft(node);
        }
        return node;
    }

    private void bubbleUp() {
        LinkedNode node = lastNodeAdded;

        T temp = node.data;
        while (node != root && temp.compareTo(node.parent.data) < 0) {
            node.data = node.parent.data;
            node = node.parent;
        }
        node.data = temp;

    }

    private LinkedNode asFarLeft(LinkedNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public String toString() {
        String result = "";
        Queue<LinkedNode> q = new ArrayDeque<LinkedNode>();
        if (root != null) {
            q.add(root);
        }
        List<T> l = new LinkedList<T>();
        while (!q.isEmpty()) {
            LinkedNode next = q.remove();
            if (next.left != null) {
                q.add(next.left);
            }
            if (next.right != null) {
                q.add(next.right);
            }
            l.add(next.data);
        }

        for (int i = 0; i < l.size(); i++) {
            if ((Math.log(i) / Math.log(2)) == 0) {
                result += "\n";
            }
            result += (l.get(i) + " ");
        }
        result += "\n";

        return result;
    }
}

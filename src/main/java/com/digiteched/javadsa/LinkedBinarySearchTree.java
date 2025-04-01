package com.digiteched.javadsa;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

import com.digiteched.javadsa.interfaces.IBinarySearchTree;

public class LinkedBinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {
    class LinkedBinaryNode implements Comparable<LinkedBinaryNode> {
        T data;

        public LinkedBinaryNode(T data) {
            this.data = data;
        }

        LinkedBinaryNode left;

        LinkedBinaryNode right;

        @Override
        public int compareTo(LinkedBinarySearchTree<T>.LinkedBinaryNode that) {
            return this.data.compareTo(that.data);
        }
    }

    private TreeIterationStrategy iterationStrategy = TreeIterationStrategy.IN_ORDER;

    private int count = 0;

    private LinkedBinaryNode root;

    @Override
    public void add(T element) {
        LinkedBinaryNode nodeToAdd = new LinkedBinaryNode(element);

        if (isEmpty()) {
            root = nodeToAdd;

            count++;

            return;
        }

        add(nodeToAdd, root);
    }

    @Override
    public void remove(T elementToRemove) {
        root = removeFrom(root, elementToRemove);

        count--;
    }

    public LinkedBinaryNode removeFrom(LinkedBinaryNode node, T elementToRemove) {
        if (node == null) {
            return null;
        }

        int comparison = elementToRemove.compareTo(node.data);

        if (comparison < 0) {
            // go left
            node.left = removeFrom(node.left, elementToRemove);
        } else if (comparison > 0) {
            // go right
            node.right = removeFrom(node.right, elementToRemove);
        } else {
            if (node.left == null) {
                return node.right;
            }

            // node with only a left child
            if (node.right == null) {
                return node.left;
            }

            // at this point, we know that we have a node with 2 children
            LinkedBinaryNode successor = inOrderSuccessor(node);

            // overwrite this data with the value for the in-order successor
            node.data = successor.data;

            node.right = removeFrom(node.right, node.data);
        }

        return node;
    }

    @Override
    public T removeMin() {
        // TODO throw custom exception if empty
        if (count == 0) {
            throw new UnsupportedOperationException();
        }
        // is the root the minimum?
        if (root.left == null) {
            LinkedBinaryNode temp = root;

            root = root.right;

            count--;

            return temp.data;
        }

        LinkedBinaryNode parent = root;
        // we know this isn't null at this point
        LinkedBinaryNode current = root.left;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }

        T result = current.data;

        /**
         * Note that the minimum doesn't have a left value, since values to the left
         * are strictly less.
         */
        parent.left = current.right;

        count--;

        return result;
    }

    @Override
    public T removeMax() {
        // TODO throw if empty

        // is the root the maximum?
        if (root.right == null) {
            LinkedBinaryNode temp = root;

            root = root.left;

            count--;

            return temp.data;
        }

        LinkedBinaryNode parent = root;
        // we know this isn't null at this point
        LinkedBinaryNode current = root.right;

        while (current.right != null) {
            parent = current;
            current = current.right;
        }

        T result = current.data;

        /**
         * TODO What if there is a "tie"?
         */
        parent.right = current.left;

        count--;

        return result;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public T getRoot() {
        return root.data;
    }

    @Override
    public boolean contains(T target) {
        return true;
    }

    private void add(LinkedBinaryNode nodeToAdd, LinkedBinaryNode root) {
        if (nodeToAdd.compareTo(root) < 0) {
            // add to left
            if (root.left == null) {
                // we've reached the destination
                root.left = nodeToAdd;
                count++;
                return;
            }

            // keep going
            add(nodeToAdd, root.left);

        } else {
            // add to right
            if (root.right == null) {
                // we've reached the destination
                root.right = nodeToAdd;
                count++;
                return;
            }

            // keep going
            add(nodeToAdd, root.right);

        }
    }

    public void setIterationStrategy(TreeIterationStrategy newStrategy) {
        iterationStrategy = newStrategy;
    }

    @Override
    public Iterator<T> iterator() {

        switch (iterationStrategy) {
            case IN_ORDER:
                return inOrderIterator();

            case PRE_ORDER:
                return preOrderIterator();

            case POST_ORDER:
                return postOrderIterator();

            case LEVEL_ORDER:
                return levelorderIterator();
            default:

                throw new UnsupportedOperationException("not supported: iteration strategy: " + iterationStrategy);

        }

    }

    private Iterator<T> inOrderIterator() {
        ArrayList<T> list = new ArrayList<>();

        inOrder(root, list);

        return list.iterator();
    }

    private void inOrder(LinkedBinaryNode node, List<T> list) {
        if (node != null) {
            // traverse left
            inOrder(node.left, list);
            // visit current
            list.add(node.data);
            // traverse right
            inOrder(node.right, list);
        }
    }

    private Iterator<T> preOrderIterator() {
        ArrayList<T> list = new ArrayList<>();

        preOrder(root, list);

        return list.iterator();
    }

    private void preOrder(LinkedBinaryNode node, List<T> list) {
        if (node != null) {
            // visit current
            list.add(node.data);
            // traverse left
            preOrder(node.left, list);

            // traverse right
            preOrder(node.right, list);
        }
    }

    private Iterator<T> postOrderIterator() {
        ArrayList<T> list = new ArrayList<>();

        postOrder(root, list);

        return list.iterator();
    }

    private void postOrder(LinkedBinaryNode node, List<T> list) {
        if (node != null) {
            // traverse left
            postOrder(node.left, list);

            // traverse right
            postOrder(node.right, list);
            // visit current
            list.add(node.data);
        }
    }

    private Iterator<T> levelorderIterator() {
        Queue<LinkedBinaryNode> q = new ArrayDeque<LinkedBinaryNode>();
        List<T> l = new ArrayList<>();
        q.add(root);

        while (!q.isEmpty()) {
            LinkedBinaryNode next = q.remove();
            if (next.left != null) {
                q.add(next.left);
            }
            if (next.right != null) {
                q.add(next.right);
            }
            l.add(next.data);
        }

        return l.iterator();
    }

    private LinkedBinaryNode inOrderSuccessor(LinkedBinaryNode parent) {
        if (parent == null || parent.right == null) {
            return null;
        }

        LinkedBinaryNode current = parent.right;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // this will only work if the binary tree is a linked binary tree because the
    // interface doesn't have inOrder
    public boolean equals(IBinarySearchTree<T> obj) {

        if ((obj instanceof LinkedBinarySearchTree<T>)) {
            LinkedBinarySearchTree<T> tree = (LinkedBinarySearchTree<T>) obj;
            if (tree == this) {
                return true;
            }

            if ((tree.size() == count)) {
                Iterator<T> thatIterator = tree.inOrderIterator();

                for (T item : this) {
                    // length is the same so we don't need to worry about tree running out of items
                    // before us
                    if (item.compareTo((T) thatIterator.next()) != 0) {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }

}

package com.digiteched.javadsa;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.digiteched.javadsa.exceptions.FailedToAddNonAlphabetValueToTrie;
import com.digiteched.javadsa.interfaces.ITrie;

public class Trie implements ITrie {
    private int startOfLowerCaseIndex = 97;
    private int endOfLowerCaseIndex = 122;

    private class Node {
        private char letter;

        private Node[] children = new Node[26];

        private boolean isEndOfWord = false;

        private Node parent;

        public Node(char letter) {
            if (letter >= startOfLowerCaseIndex && letter <= endOfLowerCaseIndex) {

                this.letter = letter;
            } else {
                if (letter == ' ') {
                    throw new FailedToAddNonAlphabetValueToTrie(letter + " (space)");
                }
                throw new FailedToAddNonAlphabetValueToTrie(letter);
            }

        }

        // this will only be used for the root
        public Node() {

        }

        public void setToEndOfWord() {
            isEndOfWord = true;
        }

    }

    private int numberOfWords = 0;
    private Node root = new Node();

    @Override
    public Iterator<String> iterator() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        List<String> words = new ArrayList<>();
        List<Node> endingWords = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node next = queue.remove();
            for (Node child : next.children) {
                if (child != null) {
                    queue.add(child);
                }
            }
            if (next.isEndOfWord) {
                endingWords.add(next);
            }

        }

        for (Node node : endingWords) {
            Stack<Node> stack = new Stack<>();

            Node temp = node;

            while (temp != root) {
                stack.push(temp);
                temp = temp.parent;
            }
            String word = "";
            while (!stack.isEmpty()) {
                word += stack.pop().letter;
            }
            words.add(word);
        }

        return words.iterator();
    }

    @Override
    public ITrie add(String word) {
        Node node = root;
        word = word.toLowerCase(); // I've decided not to remove white space, instead we just get an exception
        for (char letter : word.toCharArray()) {
            if (letter >= startOfLowerCaseIndex && letter <= endOfLowerCaseIndex) {

                if (node.children[letter - startOfLowerCaseIndex] == null) {
                    node.children[letter - startOfLowerCaseIndex] = new Node(letter);
                    node.children[letter - startOfLowerCaseIndex].parent = node;
                }
            } else {
                if (letter == ' ') {
                    throw new FailedToAddNonAlphabetValueToTrie(letter + " (space)");
                }
                throw new FailedToAddNonAlphabetValueToTrie(letter);
            }

            node = node.children[letter - startOfLowerCaseIndex];
        }
        node.setToEndOfWord();

        numberOfWords++;
        return this;
    }

    @Override
    public int size() {
        return numberOfWords;
    }

    @Override
    public boolean isEmpty() {
        return numberOfWords == 0;
    }

    @Override
    public boolean has(String word) {
        if (isEmpty()) {
            return false;
        }
        Node node = root;

        word = word.toLowerCase();
        for (char letter : word.toCharArray()) {
            if (!(letter >= startOfLowerCaseIndex && letter <= endOfLowerCaseIndex)) {
                return false; // just to make sure not to get an index out of bounds error
            }
            if (node.children[letter - startOfLowerCaseIndex] == null) {
                return false;
            }
            node = node.children[letter - startOfLowerCaseIndex];
        }

        return node.isEndOfWord;
    }

    @Override
    public List<String> getCompletions(String prefix) {
        List<String> words = new ArrayList<>();
        Node node = root;
        String currentWord = "";

        // make sure the prefix is valid
        for (char letter : prefix.toCharArray()) {
            if (!(letter >= startOfLowerCaseIndex && letter <= endOfLowerCaseIndex)) {
                return words; // I didn't want to make a new exception although I think I should
            }
            if (node.children[letter - startOfLowerCaseIndex] != null) {
                currentWord += node.letter;
                node = node.children[letter - startOfLowerCaseIndex];
            } else {
                return words;
            }
        }
        if (node.isEndOfWord) { // if prefix is a word then add it
            words.add(currentWord);
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        List<Node> endingWords = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node next = queue.remove();
            for (Node child : next.children) {
                if (child != null) {
                    queue.add(child);
                }
            }
            if (next.isEndOfWord) {
                endingWords.add(next);
            }

        }

        for (Node nodeInEndingWords : endingWords) {
            Stack<Node> stack = new Stack<>();

            Node temp = nodeInEndingWords;

            while (temp != root) {
                stack.push(temp);
                temp = temp.parent;
            }
            String word = "";
            while (!stack.isEmpty()) {
                word += stack.pop().letter;
            }
            words.add(word);
        }

        return words;
    }

}

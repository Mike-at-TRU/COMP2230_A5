package com.digiteched.javadsa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.digiteched.javadsa.exceptions.FailedToAddNonAlphabetValueToTrie;
import com.digiteched.javadsa.interfaces.ITrie;

public class Trie implements ITrie {
    private int startOfLowerCaseIndex = 97;
    private int endOfLowerCaseIndex = 122;

    private class Node {
        private char letter;

        private Node[] children = new Node[26];

        private boolean isEndOfWord = false;

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

        public void isEndOfWord() {
            isEndOfWord = true;
        }

    }

    private int numberOfWords = 0;
    private Node root = new Node();

    @Override
    public Iterator<String> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public ITrie add(String word) {
        Node node = root;
        word = word.toLowerCase(); // I've decided not to remove white space
        for (char letter : word.toCharArray()) {
            if (letter >= startOfLowerCaseIndex && letter <= endOfLowerCaseIndex) {

                if (node.children[letter - startOfLowerCaseIndex] == null) {
                    node.children[letter - startOfLowerCaseIndex] = new Node(letter);
                }
            } else {
                if (letter == ' ') {
                    throw new FailedToAddNonAlphabetValueToTrie(letter + " (space)");
                }
                throw new FailedToAddNonAlphabetValueToTrie(letter);
            }

            node = node.children[letter - startOfLowerCaseIndex];
        }
        node.isEndOfWord();

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
                return words; // I didn't want to make a new exception although I think should so I'm just
                              // returning an empty list
            }
            if (node.children[letter - startOfLowerCaseIndex] != null) {
                currentWord += node.letter;
                node = node.children[letter - startOfLowerCaseIndex];
            } else {
                return words;
            }
        }
        if (node.isEndOfWord) {
            words.add(currentWord);
        }

        return words;
    }

}

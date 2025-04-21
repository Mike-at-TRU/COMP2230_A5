package com.digiteched.javadsa;

import com.digiteched.javadsa.interfaces.IMinHeap;

public class Main {
    public static void main(String[] args) {
        // Integer[] numbers = { 9, 6, 8, 12, 3, 1, 7 };

        // SearchAndSort.heapSort(numbers);

        Trie trie = new Trie();
        System.out.println("before adding values");
        for (String string : trie) {
            System.out.println(string);
        }

        trie.add("apple").add("ant").add("and").add("antler").add("bull").add("bully").add("bullhorn").add("bulldozer")
                .add("bullshit");

        System.out.println("\nafter adding values");
        for (String string : trie) {
            System.out.println(string);
        }

        System.out.println("\nbul complitions");
        trie.getCompletions("bul").forEach((completion) -> {
            System.out.println(completion);
        });
    }
}

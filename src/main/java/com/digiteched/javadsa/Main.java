package com.digiteched.javadsa;

import com.digiteched.javadsa.interfaces.IMinHeap;

public class Main {
    public static void main(String[] args) {
        IMinHeap<Integer> h = new LinkedMinHeap<>();

        for (int i = 10; i >= 0; i--) {
            h.add(i);
            System.out.println(h);
        }
        // Trie trie = new Trie();

        // trie.add("apple").add("ant").add("and").add("antler").add("bull").add("bully").add("bullhorn").add("bulldozer")
        // .add("bullshit");

        // trie.getCompletions("bul").forEach((completion) ->{
        // System.out.println(completion);});
    }
}

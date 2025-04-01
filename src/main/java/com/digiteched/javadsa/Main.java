package com.digiteched.javadsa;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.add("apple").add("ant").add("and").add("antler").add("bull").add("bully").add("bullhorn").add("bulldozer")
                .add("bullshit");

        trie.getCompletions("bul").forEach((completion) ->{ System.out.println(completion);});
    }
}

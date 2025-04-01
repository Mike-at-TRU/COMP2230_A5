package com.digiteched.javadsa.interfaces;

import java.util.List;

public interface ITrie extends Iterable<String>{
    ITrie add(String word);

    int size();

    boolean isEmpty();

    boolean has(String word);

    List<String> getCompletions(String prefix);
}

package com.digiteched.javadsa.exceptions;

public class FailedToAddNonAlphabetValueToTrie extends RuntimeException {
    public FailedToAddNonAlphabetValueToTrie() {
        super("The value given is not included in the alphabet and can not be added");
    }

    public FailedToAddNonAlphabetValueToTrie(String value) {
        super(value + " is not included in the alphabet and can not be added");
    }

    public FailedToAddNonAlphabetValueToTrie(char value) {
        super(value + " is not included in the alphabet and can not be added");
    }
}

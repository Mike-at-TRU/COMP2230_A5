package com.digiteched.javadsa.exceptions;

public class FailedToAddNonAlphabetValueToTry extends RuntimeException {
    public FailedToAddNonAlphabetValueToTry() {
        super("The value given is not included in the alphabet and can not be added");
    }

    public FailedToAddNonAlphabetValueToTry(String value) {
        super(value + " is not included in the alphabet and can not be added");
    }

    public FailedToAddNonAlphabetValueToTry(char value) {
        super(value + " is not included in the alphabet and can not be added");
    }
}

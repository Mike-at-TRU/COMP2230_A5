package com.digiteched.javadsa.exceptions;

public class FailedToDequeueFromEmptyQueueException extends RuntimeException {
    public FailedToDequeueFromEmptyQueueException(){
        super("You cannot dequeue from an empty Queue.");
    }
}

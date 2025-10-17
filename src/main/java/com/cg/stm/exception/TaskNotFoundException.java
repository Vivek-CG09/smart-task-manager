package com.cg.stm.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}

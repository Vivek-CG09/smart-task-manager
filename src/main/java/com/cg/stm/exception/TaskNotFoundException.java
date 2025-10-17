package com.cg.stm.exception;

/**
 * Exception thrown when a requested task cannot be found.
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * Constructs a new TaskNotFoundException with a default message.
     */
    public TaskNotFoundException() {
        super("Task not found");
    }

    /**
     * Constructs a new TaskNotFoundException with the specified detail message.
     *
     * @param cause the detail message explaining why the task was not found
     */
    public TaskNotFoundException(String cause) {
        super(cause);
    }
}

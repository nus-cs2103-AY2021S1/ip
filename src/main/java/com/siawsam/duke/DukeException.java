package com.siawsam.duke;

/**
 * Represents all exceptions related to the Duke program.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException.
     *
     * @param message A string containing the exception message.
     */
    DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a DukeException from an existing throwable.
     *
     * @param message A string containing the exception message.
     * @param error A throwable related to the exception being created.
     */
    DukeException(String message, Throwable error) {
        super(message, error);
    }
}

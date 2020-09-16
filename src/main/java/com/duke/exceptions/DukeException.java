package com.duke.exceptions;

/**
 * Exception thrown when an unexpected event related to using the Duke application occurs.
 */

public class DukeException extends Exception {

    /**
     * Class constructor.
     *
     * @param message Message attached to exception thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}

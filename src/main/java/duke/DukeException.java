package main.java.duke;

/**
 * DukeException is an exception class for Duke. 
 * It will throw an exception when user's input command cannot be recognised by Duke.
 */
public class DukeException extends IllegalArgumentException {
    DukeException(String message) {
        super(message);
    }
}

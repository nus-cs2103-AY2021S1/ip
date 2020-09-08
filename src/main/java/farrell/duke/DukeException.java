package main.java.farrell.duke;

/**
 * Signals that the program has encountered a problem.
 */
public class DukeException extends Exception {
    DukeException(String message) {
        super("Error! " + message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

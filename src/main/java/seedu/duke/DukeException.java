package seedu.duke;

/**
 * Exception for Duke program.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(("ERROR!! " + message).replaceAll("(?m)^", "\t"));
    }
}
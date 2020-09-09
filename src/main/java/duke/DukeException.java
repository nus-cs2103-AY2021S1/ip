package duke;

/**
 * Creates a customised exception called DukeException with customised error message.
 */
public class DukeException extends Exception {
    DukeException(String s) {
        super(s);
    }
}

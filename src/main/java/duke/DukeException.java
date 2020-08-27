package duke;

/**
 * Creates a customised exception with customised error message.
 */
public class DukeException extends Exception {
    DukeException(String s) {
        super(s);
    }
}

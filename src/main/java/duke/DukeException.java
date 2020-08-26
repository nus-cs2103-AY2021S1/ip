package duke;

/**
 * The DukeException class throws an exception if the Duke program
 * does not recognise an input from the user.
 */
public class DukeException extends Exception {
    DukeException(String s) {
        super(s);
    }
}

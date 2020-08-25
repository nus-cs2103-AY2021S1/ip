package duke.component;

/**
 * The exception to be thrown when Duke encounters a problem
 */
public class DukeException extends Exception {
    public DukeException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DukeException(String msg) {
        super("\u2639 OOPS!!! " + msg);
    }
}

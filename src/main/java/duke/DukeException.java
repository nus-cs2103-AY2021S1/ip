package duke;

public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    /**
     * A Duke exception.
     *
     * @param message The exception message.
     */
    public DukeException(String message) {
        super("\u2639 OOPS!!! " + message);
        assert(!message.isBlank()) : "DukeException should not have an empty message";
    }
}

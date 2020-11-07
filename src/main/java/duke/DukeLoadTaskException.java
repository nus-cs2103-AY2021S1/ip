package duke;

/**
 * Represents an exception that signifies an error when loading tasks.
 */
public class DukeLoadTaskException extends DukeException {

    /**
     * Class constructor.
     */
    public DukeLoadTaskException() {
        super("OOPS!!! I'm sorry, but I don't know what that line in the txt file means.");
    }
}

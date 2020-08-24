package duke;

/**
 * Represents exceptions in the Duke program.
 */
public class DukeException extends Exception{

    /**
     * Initializes a newly created DukeException with a message.
     * @param msg message of the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}

package duke.exception;

public class TodoException extends DukeException {

    /**
     * Constructs the todo exception for invalid details.
     */
    public TodoException() {
        super("Please specify what do you want to do~");
    }

}

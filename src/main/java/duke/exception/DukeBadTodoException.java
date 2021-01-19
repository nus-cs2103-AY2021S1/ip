package duke.exception;

public class DukeBadTodoException extends DukeException {
    private static final String BAD_TODO_ERROR = "HELLO! Please fill up a description of todo. "
            + "Can't be empty!!! \uD83D\uDE20";

    /**
     * Initialises DukeBadTodoException object by calling constructor of DukeException object with appropriate message
     */
    public DukeBadTodoException() {
        super(HORIZONTAL_RULE + "\n" + BAD_TODO_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

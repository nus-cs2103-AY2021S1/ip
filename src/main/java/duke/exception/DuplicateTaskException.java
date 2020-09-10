package duke.exception;

public class DuplicateTaskException extends DukeException {
    private static final String ERROR_MESSAGE = "Oh dear, it seems you have created a similar task before!\n\n";
    /**
     * Initialises a new DukeException.
     * @param duplicatedTask A string that describes the error, to be displayed to the user.
     */
    public DuplicateTaskException(String duplicatedTask) {
        super(ERROR_MESSAGE + duplicatedTask);
    }
}

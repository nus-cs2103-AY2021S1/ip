package meimei.dukeexception;

/**
 * Exception thrown when a non-valid user command is inputted.
 */
public class NoCommandException extends DukeException {

    /**
     * Public constructor.
     */
    public NoCommandException() {
        super("I cannot find this command leh. Try sth else?");
    }
}

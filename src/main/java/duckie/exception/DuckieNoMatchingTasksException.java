package duckie.exception;

/**
 * DuckieException thrown when the input keyword does not match with any tasks.
 */
public class DuckieNoMatchingTasksException extends DuckieException {
    /**
     * Instantiates DuckieNoMatchingTasksException.
     */
    public DuckieNoMatchingTasksException() {
        super("Duckie can't find any matching tasks!");
    }

    /**
     * Instantiates DuckieNoMatchingTasksException with slightly customised message.
     */
    public DuckieNoMatchingTasksException(String message) {
        super("Duckie can't find any matching " + message + " tasks!");
    }

}

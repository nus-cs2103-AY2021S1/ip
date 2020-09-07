package duckie.exception;

/**
 * DuckieException thrown when the input keyword does not match with any tasks.
 */
public class DuckieNoMatchingTasksException extends DuckieException {
    /**
     * Instantiate DuckieNoListException.
     */
    public DuckieNoMatchingTasksException() {
        super("Duckie can't find any matching tasks!");
    }
}

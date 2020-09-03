package rogue.logic.directives.exceptions;

/**
 * Represents an error when the an {@code Executable} cannot be executed
 * successfully.
 */
public class ExecutionException extends Exception {
    public ExecutionException(String description) {
        super(description);
    }
}

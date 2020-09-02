package rogue.logic.exceptions;

/**
 * Represents an error when the arguments for an {@code Action} are not
 * valid.
 */
public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String description) {
        super(description);
    }
}

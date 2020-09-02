package rogue.logic.parser.exceptions;

/**
 * Represents an error when a user input is not understood
 * by {@code Rogue}.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String description) {
        super(description);
    }
}

package rogue.logic.parser.exceptions;

/**
 * Represents an error when a user input is not understood
 * by the {@code Parser} of {@code Rogue}.
 */
public class IncorrectInputException extends Exception {
    public IncorrectInputException(String description) {
        super(description);
    }
}

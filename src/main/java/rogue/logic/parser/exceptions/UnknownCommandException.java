package rogue.logic.parser.exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String description) {
        super(description);
    }
}

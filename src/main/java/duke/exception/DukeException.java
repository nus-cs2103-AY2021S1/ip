package duke.exception;

/**
 * Represents a general DukeException in the program.
 * Contains a <code>String</code> message for the exception and
 * a <code>String</code> exceptionType to identify the exception.
 */
public abstract class DukeException extends Exception {
    private final String exceptionType;

    public DukeException(String exceptionMessage, String exceptionType) {
        super(exceptionMessage);
        this.exceptionType = exceptionType;
    }

    @Override
    public String toString() {
        return "DukeException: " + this.exceptionType + "\n" +
                getMessage() + "\n" +
                "See \"help\" for more";
    }
}

package duke.exception;

/**
 * Represents a generic DukeException.
 * It contains a <code>String</code> exceptionType describing the type of <code>DukeException</code>,
 * a <code>String</code> additionalInfo to describe the <code>DukeException</code> in detail.
 */
public abstract class DukeException extends Exception {
    private final String exceptionType;
    private final String additionalInfo;

    public DukeException(String message, String exceptionType, String additionalInfo) {
        super(message);
        this.exceptionType = exceptionType;
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Exception: " + this.exceptionType +
                "\n\t" + getMessage() +
                "\n\t" + additionalInfo +
                "\n\tSee \"help\" for more";
    }
}
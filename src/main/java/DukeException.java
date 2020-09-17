/**
 * Exception class that is specific to the Duke application.
 */
public class DukeException extends Exception {
    
    public enum DukeExceptionType {
        TODO,
        EVENT,
        DEADLINE,
        VIEW_SCHEDULE,
        DELETE,
        DONE,
        FIND
    }

    protected DukeExceptionType dukeExceptionType;

    /**
     * Constructor that creates a DukeException.
     * @param message the error message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Overloaded constructor that creates a DukeException with specified
     * dukeExceptionType.
     * @param message the error message of the Exception.
     * @param dukeExceptionType the type of Exception.
     */
    public DukeException(String message, DukeExceptionType dukeExceptionType) {
        super(message);
        this.dukeExceptionType = dukeExceptionType;
    }
    
    @Override
    public String toString() {
        return String.format("Error: %s", getMessage());
    }
}

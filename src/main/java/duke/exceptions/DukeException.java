package duke.exceptions;

/**
 * Exception thrown when user enters invalid input
 */
public class DukeException extends Exception {
    
    protected String exceptionMessage;

    /**
     * Sets the exception message to the specified message
     * @param exceptionMessage specified message 
     */
    public DukeException (String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Returns the exception message
     * @return exception message
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }
}

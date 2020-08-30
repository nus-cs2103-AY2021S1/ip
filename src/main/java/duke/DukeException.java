package duke;

/**
 * An exception class that handles user input exceptions.
 */
class DukeException extends Exception {
    private String error;

    /**
     * Constructor
     * @param error the error message to show
     */
    DukeException(String error) {
        // TODO: use super(error) constructor instead
        this.error = error;
    }

    /**
     * <code>String</code> represenation of this exception.
     * @return the string representation of this exception.
     */
    @Override
    public String toString() {
        return String.format("OOPS! %s", error);
    }
}

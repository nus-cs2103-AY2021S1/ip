package duke;

/**
 * MissingTaskNumberException is thrown when the task number is not inputted with the done or delete command.
 */
public class MissingTaskNumberException extends Exception{

    /**
     * Formats the string of MissingTaskNumberException.
     *
     * @param message Takes in the error message to be printed.
     */
    public MissingTaskNumberException(String message) {
        super(message);
    }
}

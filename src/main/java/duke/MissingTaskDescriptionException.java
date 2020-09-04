package duke;

/**
 * MissingTaskDescriptionException is thrown when the task description is not inputted with the 
 * todo, deadline or event command.
 */
public class MissingTaskDescriptionException extends Exception {

    /**
     * Formats the string of MissingTaskDescriptionException.
     * 
     * @param message Takes in the error message to be printed.
     */
    public MissingTaskDescriptionException(String message) {
        super(message);
    }
}

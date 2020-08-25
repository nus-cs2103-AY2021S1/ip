package duke.exception;

/**
 * Encapsulates unknown command exceptions. These exceptions are thrown when the user enters an invalid command in
 * which the first word of the command is not a recognized command word.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Returns an error message. Informs the user that the command entered is not recognized.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " I'm sorry, but I " +
                "don't know what that means :(";
    }
}

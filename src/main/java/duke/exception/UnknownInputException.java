package duke.exception;

/**
 * Exception that occurs when the user inputs an invalid command that Duke does not understand.
 */
public class UnknownInputException extends DukeException {

    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Sorry, but I do not know what that means :(";
    }
}

package duke.exception;

/**
 * Exception that occurs when the user input an invalid number for the file that wants to be deleted.
 */
public class DeleteOutOfListException extends DukeException {

    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Task number cannot be zero or negative"
                + "\n" + " Please refer to your task list to find the"
                + "\n" + " appropriate task number :)";
    }
}

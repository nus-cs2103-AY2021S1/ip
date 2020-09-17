package duke.exception;

/**
 * Exception that occurs when the user does not specify the task number as an integer.
 */
public class InvalidFormatTaskNumberException extends InvalidInputException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return " Oh no! Task number must be an integer"
            + "\n" + " Please refer to your task list to find the"
            + "\n" + " appropriate task number :)";
    }
}

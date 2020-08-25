package duke;

/**
 * Represents the custom exception class for Duke Project.
 */
public class DukeException extends Exception {
    protected String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the error message to the user in a readable fashion.
     *
     * @return Error Message.
     */
    @Override
    public String getMessage() {
        return "********ERROR********\n" + "OOPS!!! " + this.message + "\n********ERROR********";
    }
}

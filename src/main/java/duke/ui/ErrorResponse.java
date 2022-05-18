package duke.ui;

/**
 * Represents a Response sent to the GUI when an error has occurred
 */
public class ErrorResponse extends Response {

    /**
     * Constructs an Error Response for the GUI
     *
     * @param isExit whether the GUI should exit
     * @param message message the GUI should display to user
     */
    public ErrorResponse(boolean isExit, String message) {
        super(isExit, message);
        this.isError = true;
    }
}

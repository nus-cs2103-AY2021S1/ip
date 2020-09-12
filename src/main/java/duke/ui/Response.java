package duke.ui;

/**
 * Represents a response sent by the Duke logic to the GUI.
 */
public class Response {

    /** Whether the GUI should exit **/
    private boolean isExit;

    /** Message the GUI should display to user **/
    private String message;

    /** Whether an error has occurred **/
    protected boolean isError;

    /**
     * Constructs a Response for the GUI.
     *
     * @param isExit whether the GUI should exit
     * @param message message the GUI should display to user
     */
    public Response(boolean isExit, String message) {
        this.isExit = isExit;
        this.message = message;
        this.isError = false;
    }

    public boolean shouldExit() {
        return isExit;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() { return isError; }
}

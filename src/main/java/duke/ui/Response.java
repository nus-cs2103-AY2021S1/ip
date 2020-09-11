package duke.ui;

/**
 * Represents a response sent by the Duke logic to the GUI.
 */
public class Response {

    /** Whether the GUI should exit **/
    private boolean isExit;

    /** Message the GUI should display to user **/
    private String message;

    /**
     * Constructs a Response for the GUI.
     *
     * @param isExit whether the GUI should exit
     * @param message message the GUI should display to user
     */
    public Response(boolean isExit, String message) {
        this.isExit = isExit;
        this.message = message;
    }

    public boolean shouldExit() {
        return isExit;
    }

    public String getMessage() {
        return message;
    }
}

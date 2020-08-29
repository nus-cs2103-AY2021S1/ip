package duke.ui;

/**
 * Represents a response sent by the Duke logic to the GUI.
 */
public class Response {

    private boolean isExit;
    private String message;

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

package duke;

/**
 * Encapsulates behaviour for all UI related actions.
 */
public class UI {

    private String nextResponse;

    /**
     * Creates a new instance of the UI class.
     */
    public UI() {
    }

    /**
     * Greets the user.
     */
    public static String greet() {
        return "Hi I'm Duke! How can I help you today?";
    }

    /**
     * Stores the next response that needs to be displayed in GUI.
     *
     * @param message Message to be printed.
     */
    public void printToConsole(String message) {
        assert message != null : "Message to be printed cannot be null";
        this.nextResponse = message;
    }

    /**
     * Returns the next response that needs to be displayed in GUI.
     *
     * @return Response to be displayed in GUI.
     */
    public String getNextResponse() {
        return nextResponse;
    }
}

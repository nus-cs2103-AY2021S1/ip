package duke;

/**
 * The Response class encapsulates the Response given by Duke.
 */
public class Response {
    private String message;
    private boolean isError;

    /**
     * Instantiates a Response object with the given message and isError flag.
     *
     * @param message Message of Duke's response.
     * @param isError Flag to indicate if the input is invalid.
     */
    public Response(String message, Boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Returns the message associated with this Response.
     *
     * @return Message of the Response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the error status of the Response.
     *
     * @return Whether the Response indicates an error.
     */
    public boolean isErroneous() {
        return isError;
    }
}

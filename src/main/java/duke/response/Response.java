package duke.response;

public class Response {
    private final String message;
    private final boolean isError;
    private final boolean isExit;

    public Response() {
        this(null, false, false);
    }

    /**
     * A Response object.
     *
     * @param message The message string.
     * @param isError The error status.
     * @param isExit The exit status.
     */
    public Response(String message, boolean isError, boolean isExit) {
        this.message = message;
        this.isError = isError;
        this.isExit = isExit;
    }

    public Response(String message) {
        this(message, false, false);
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }

    public boolean isExit() {
        return this.isExit;
    }
}

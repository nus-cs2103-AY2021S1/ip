package duke.response;

// Class that contains the response message for duke
public abstract class Response {
    private String message;
    private boolean isError;

    protected Response(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }
}

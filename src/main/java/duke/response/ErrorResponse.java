package duke.response;

public class ErrorResponse extends Response {
    public ErrorResponse(String message) {
        super(message, true);
    }
}

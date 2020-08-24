package duke;

public class ResponseException extends Exception {
    public ResponseException(String errorMessage) {
        super(errorMessage);
    }
}

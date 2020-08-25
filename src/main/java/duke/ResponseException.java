package duke;

/**
 * ResponseException handles errors when the response is not understood by Parser
 */
public class ResponseException extends Exception {
    public ResponseException(String errorMessage) {
        super(errorMessage);
    }
}

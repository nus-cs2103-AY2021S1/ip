package duke.common;

/**
 * Signals custom errors and outputs a corresponding message.
 */
public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }
}

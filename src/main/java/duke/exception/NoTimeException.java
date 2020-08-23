package duke.exception;

public class NoTimeException extends Exception {
    public NoTimeException(String type) {
        super("☹ OOPS!!! The time of a " + type + " cannot be empty.");
    }
}

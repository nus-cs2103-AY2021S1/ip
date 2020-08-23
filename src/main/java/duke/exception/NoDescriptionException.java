package duke.exception;

public class NoDescriptionException extends Exception {
    public NoDescriptionException(String type) {
        super("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }
}

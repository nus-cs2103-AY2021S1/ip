package duke;

public class NoDescriptionException extends Exception {
    public NoDescriptionException(String message) {
        super(message + "\n");
    }
}

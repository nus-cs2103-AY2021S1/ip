package main.exception;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("     ☹ OOPS!!! Your selected task does not exist!");
    }
}

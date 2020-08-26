package duke;

public class InvalidTypeException extends DukeException {
    public InvalidTypeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
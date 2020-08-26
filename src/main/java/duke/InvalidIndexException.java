package duke;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("OOPS!!! The index you have chosen is out of bounds");
    }
}

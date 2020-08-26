package duke;

public class InvalidDataException extends DukeException {
    public InvalidDataException() {
        super("OOPS!!! The data here is invalid!");
    }
}
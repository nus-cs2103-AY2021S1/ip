package duke.exception;

public class InvalidToDoException extends DukeException {
    public InvalidToDoException() {
        super("The description of a todo cannot be empty :(\n"
                + "The command format is \"todo <task>\"");
    }
}

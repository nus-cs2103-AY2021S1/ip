package duke.exceptions;

public class EmptyEventDateException extends DukeException {

    public EmptyEventDateException() {
        super("OOPS! The date / time of event cannot be empty!");
    }
}

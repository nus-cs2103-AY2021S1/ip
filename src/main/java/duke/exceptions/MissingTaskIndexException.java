package duke.exceptions;

public class MissingTaskIndexException extends DukeException {

    public MissingTaskIndexException() {
        super("☹ OOPS!!! There isn't a task index inputted");
    }
}

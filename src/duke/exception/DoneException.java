package duke.exception;

public class DoneException extends DukeException {
    public DoneException() {
        super("☹ OOPS!!! You need a task number to use done!");
    }
}

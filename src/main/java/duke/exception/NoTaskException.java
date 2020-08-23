package duke.exception;

public class NoTaskException extends DukeException{
    public NoTaskException() {
        super("OOPS!!! You have no task currently.");
    }
}

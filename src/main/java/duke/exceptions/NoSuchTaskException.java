package duke.exceptions;

public class NoSuchTaskException extends DukeException {

    public NoSuchTaskException() {
        super("No such task exists",
                "OOPS! No such task exists, please try again.");
    }
}

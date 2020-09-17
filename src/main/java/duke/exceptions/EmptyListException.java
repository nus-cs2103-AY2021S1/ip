package duke.exceptions;

public class EmptyListException extends DukeException {
    private static String errorMsg = "You ain't no tasks yet!";

    public EmptyListException() {
        super(errorMsg);
    }
}

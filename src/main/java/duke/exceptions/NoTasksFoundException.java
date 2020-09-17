package duke.exceptions;

public class NoTasksFoundException extends DukeException {
    private static String errorMsg = "There ain't no matching tasks in your list!\n";

    public NoTasksFoundException() {
        super(errorMsg);
    }
}

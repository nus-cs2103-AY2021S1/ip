package focus.exception;

/** Represents exception when user wants to view task list but there is no tasks. */
public class NoTasksException extends FocusException {
    /** Creates NoTasksException for ListCommand and RemindCommand classes to throw. */
    public NoTasksException() {
        super("There are currently no tasks on your list!\n"
                + "\tStart adding one now!");
    }
}

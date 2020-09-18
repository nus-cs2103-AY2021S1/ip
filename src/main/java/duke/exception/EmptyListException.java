package duke.exception;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("There are no tasks on your list\n"
                + "Use the 'add' command to start adding tasks!\n");
    }
}

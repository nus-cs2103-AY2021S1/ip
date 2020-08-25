package duke.exception;

public class TaskExistException extends DukeException{

    public TaskExistException() {
        super("You task has been in the list. Please change the description.");
    }
}

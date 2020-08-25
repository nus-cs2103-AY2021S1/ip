package duke.exception;

public class DoneIndexEmptyException extends DukeException{

    public DoneIndexEmptyException() {
        super("Your index for done command should not be empty");
    }
}

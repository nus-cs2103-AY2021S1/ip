package duke.exception;

public class AlreadyDoneIndexException extends DukeException {

    public AlreadyDoneIndexException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "This task is already done!";
    }
}

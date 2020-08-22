package duke.exception;

public class DukeInvalidKeywordException extends DukeException{
    public DukeInvalidKeywordException() {
        super("Please enter a valid keyword!");
    }
}

package duke.exception;

public class MissingKeywordException extends DukeException {
    public MissingKeywordException(String message) {
        super(String.format("%s Please specify a keyword!", message));
    }
}

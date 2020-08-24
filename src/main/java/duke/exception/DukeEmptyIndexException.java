package duke.exception;

public class DukeEmptyIndexException extends DukeIndexException {
    public DukeEmptyIndexException(String type) {
        super(String.format("☹ OOPS!!! The index after %s cannot be empty.", type));
    }
}

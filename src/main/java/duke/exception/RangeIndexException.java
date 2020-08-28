package duke.exception;

public class RangeIndexException extends DukeException {

    public RangeIndexException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Index out of range!";
    }
}

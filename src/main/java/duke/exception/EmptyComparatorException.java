package duke.exception;

public class EmptyComparatorException extends DukeException {
    public EmptyComparatorException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Comparator cannot be blank!";
    }
}

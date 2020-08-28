package duke.exception;

public class DukeNoItemToDeleteException extends DukeException {
    public DukeNoItemToDeleteException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "ERROR: Duke doesn't know what to delete -> " + input;
    }
}

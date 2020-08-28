package duke.exception;

public class DukeNoItemToMarkDoneException extends DukeException {
    public DukeNoItemToMarkDoneException(String input) {
        super(input);
    }
    
    @Override
    public String toString() {
        return "ERROR: Duke doesn't know what to mark as done -> " + input;
    }
}

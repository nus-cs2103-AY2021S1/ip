package duke.exception;

public class DukeNoDescriptionException extends DukeException {
    public DukeNoDescriptionException(String input) {
        super(input);
    }
    
    @Override
    public String toString() {
        return "ERROR: Duke can't find your task details -> " + input;
    }
}

package duke.exception;

public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "ERROR: Duke can't recognise your command -> " + input;
    }
}

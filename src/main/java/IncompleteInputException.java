public class IncompleteInputException extends DukeException {

    public IncompleteInputException() {
        super("Missing description and/or date.");
    }
}

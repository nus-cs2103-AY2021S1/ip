package duke.exception;

public class InvalidNumberInputException extends DukeException {
    public InvalidNumberInputException() {
        super("Sorry this is not a number.");
    }
}

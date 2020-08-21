package duke.exception;

/**
 * Represents a Duke exception in which the user input cannot be recognised by the parser.
 */
public class DukeUnknownInputException extends DukeException {

    @Override
    public String toString() {
        return "ERROR: Unknown input! Try again.";
    }

}

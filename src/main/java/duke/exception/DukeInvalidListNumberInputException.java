package duke.exception;

/**
 * Represents a Duke exception in which the list number input is invalid.
 */
public class DukeInvalidListNumberInputException extends DukeException {
    @Override
    public String toString() {
        return "ERROR: Invalid list number input!";
    }
}

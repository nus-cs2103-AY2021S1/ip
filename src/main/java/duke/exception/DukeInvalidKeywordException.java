package duke.exception;

/**
 * Represents a Duke exception in which the keyword is empty (that is to say: invalid).
 */
public class DukeInvalidKeywordException extends DukeException {

    @Override
    public String toString() {
        return "ERROR: The keyword cannot be empty!";
    }

}

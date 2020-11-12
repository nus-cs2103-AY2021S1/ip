package duke;

/**
 * Represents any Exception specific to the Duke program
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Paiseh ah, " + super.getMessage();
    }
}

/**
 * Represents exceptions for the Duke program
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + super.toString();
    }
}

/**
 * Represents an exception arising from the flow of the Duke program.
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.message;
    }
}

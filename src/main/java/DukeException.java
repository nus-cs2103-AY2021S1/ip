/**
 * Represents exceptions thrown in Duke.
 */
public class DukeException extends Exception {

    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}

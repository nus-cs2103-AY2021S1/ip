/**
 * Represents an exception that occurs due to running Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a duke exception.
     *
     * @param msg error message
     */
    DukeException(String msg) {
        super(msg);
    }

    public String toString() {
        return this.getMessage();
    }
}

package duke;

/**
 * Represents exceptions that may happen while running Duke.
 */
public class DukeException extends Exception {
    /**
     * The type of DukeException caught.
     */
    private String type;

    /**
     * Creates a new <code>DukeException</code> with the given <code>type</code>.
     */
    public DukeException (String type) {
        this.type = type;
    }

    /**
     * Returns the type of a DukeException.
     * @return the type of this exception.
     */
    public String getType() {
        return this.type;
    }
}
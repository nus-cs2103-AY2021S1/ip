package duke.exceptions;

/**
 * Exceptions specialised to the ip project.
 */
public class DukeException extends Exception {
    private String description;

    /**
     * DukeException constructor.
     * @param description Error message.
     */
    public DukeException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}

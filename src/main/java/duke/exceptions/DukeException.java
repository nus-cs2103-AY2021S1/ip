package duke.exceptions;

public class DukeException extends Exception {
    private String description;

    public DukeException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}

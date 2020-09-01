package duke;

/**
 * Represents a ToDo task
 */
public class ToDo extends Task {

    /**
     * Initialise a ToDo object
     * @param description  Description of ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;

/**
 * An extension of the Task class of the type ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object.
     *
     * @param description {@inheritDoc}
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "T";
    }
}

package duke.task;

/**
 * Class representing a generic task with no dates attached.
 */
public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object representing the given command.
     *
     * @param task Command describing the ToDo to be created.
     * @return ToDo object representing the task description.
     */
    public static ToDo create(String task) {
        return new ToDo(task);
    }

    /**
     * Creates a new ToDo object represented by its String when read from a file.
     *
     * @param description Description of task.
     * @return Event object representing the given details.
     */
    public static ToDo createFromFile(String description) {
        return new ToDo(description);
    }

    @Override
    public String toDataString() {
        return "T | "
                + (isDone ? 1 : 0) + " | "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDo) {
            return description.equals(((ToDo) obj).description);
        } else {
            return false;
        }
    }
}

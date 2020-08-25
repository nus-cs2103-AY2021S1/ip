package duke.tasks;

/**
 * Simple task with no date.
 */
public class Todo extends Task {


    /**
     * Constructor to create Todo object.
     *
     * @param description specific details of the todo event.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints out the task in correct format.
     *
     * @return task in the form of a string.
     */
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

}

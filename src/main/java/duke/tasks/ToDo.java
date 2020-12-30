package duke.tasks;

/**
 * A class that represents the a task that needs to be done.
 */
public class ToDo extends Task {

    /**
     * Constructs the toDo class.
     *
     * @param description the string of description of the toDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the toDo task.
     *
     * @return the string representation of toDo task, including status icon,
     *         and description.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}

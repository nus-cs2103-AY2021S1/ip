package duke.task;

/**
 * The ToDo class contains the name and completion status
 * of a task.
 */
public class ToDo extends Task {

    /**
     * Constructor of a ToDo object.
     *
     * @param name The name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of a ToDo object.
     *
     * @return A String which contains the name and completion
     * status of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
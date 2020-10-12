package duke.tasks;

/**
 * Represents a todo task saved by the user.
 */
public class ToDo extends Task {

    /**
     * Creates an instace of a todo task with the appropriate task description.
     *
     * @param toDoName Description of todo task.
     */
    public ToDo(String toDoName) {
        super(toDoName);
    }

    /**
     * Returns description of todo task.
     *
     * @return Description of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts todo task to a savable format.
     *
     * @return Savable format of todo task.
     */
    @Override
    public String getSaveFormat() {
        return "T" + " | " + super.getSaveFormat();
    }
}

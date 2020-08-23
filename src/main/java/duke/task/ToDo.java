package duke.task;

/**
 * Represents task with a type of To-Do.
 */
public class ToDo extends Task {

    /**
     * Creates To-Do with user provided description.
     * @param description Description of To-Do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts To-Do to string form to be saved in user's files.
     * @return String format of To-Do task.
     */
    @Override
    public String taskToText() {
        return "T|" + super.completed + "|" + super.taskName;
    }

    /**
     * Returns string format of To-Do.
     * @return A string representation of To-Do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

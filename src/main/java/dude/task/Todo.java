package dude.task;


/**
 * Todo handles the tasks that only contains descriptions
 */
public class Todo extends Task {
    /**
     * Constructor for Todo class.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Another constructor for the Todo class.
     *
     * @param description description of the task.
     * @param isDone boolean value to denote if a task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the task to be written into the data file.
     *
     * @return String formatted description.
     */
    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     *
     * @return String formatted description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

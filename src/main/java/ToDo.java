/**
 * Todo task that inherits from Task class
 */
public class ToDo extends Task {

    /**
     * Creates a new Task object
     *
     * @param description details about the ToDo task
     * @return ToDo task with a corresponding description and sets it as uncompleted.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

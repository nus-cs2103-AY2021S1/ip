package duckie.task;

/**
 * A task type in charge of task description only
 */
public class Todo extends Task {
    /**
     * Instantiate Todo object
     * @param description Description of a Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides method to return the type of the Todo Task
     * @return "T" string
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Overrides method to return the String representation of a Todo task
     * @return String representation of Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

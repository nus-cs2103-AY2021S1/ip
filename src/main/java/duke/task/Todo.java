package duke.task;

/**
 * Represents a task with no specific due date or location
 */
public class Todo extends Task {

    /**
     * The constructor of the Todo class.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return a string indicating the details of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

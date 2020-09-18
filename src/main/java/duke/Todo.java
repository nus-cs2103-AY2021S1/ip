package duke;

/**
 * Represents a Todo task
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo.
     * @param description the description of the task.
     * @param isDone boolean to indicates whether the task has been done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the format of the Todo to be saved in hard disk.
     * @return Todo object in specified format.
     */
    @Override
    public String getData() {
        return "TODO#" + description + "#" + String.valueOf(isDone) + "#" + tag;
    }

    /**
     * Gets the string representation of the Todo object.
     * @return the string representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

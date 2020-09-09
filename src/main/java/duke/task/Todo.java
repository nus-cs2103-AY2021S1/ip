package duke.task;

public class Todo extends Task {

    /**
     * Constructor for Todo
     *
     * @param description description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo
     *
     * @param description description of todo task.
     * @param isDone      whether or not a task has been done.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns task printing with proper formatting.
     *
     * @return String with task formatting
     */
    public String saveText() {
        return "T | " + getStatusIcon() + " | " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

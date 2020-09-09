package duke.tasks;

/**
 * Todo class which extends from the Task class to
 * indicate a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for a new todo task.
     *
     * @param description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getState() {
        return "T|"
                + (this.isDone ? "1" : "0") + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

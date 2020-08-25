package duke.task;

/**
 * A kind of tasks without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Default constructor for Todo.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Construct a Todo task assigned the done status.
     * @param isDone the done status of the task.
     * @param description the description of the task.
     */
    public Todo(boolean isDone, String description) {
        this(description);
        this.isDone = isDone;
    }


    /**
     * Return the String to be store in files.
     * @return the String to be store in files.
     */
    @Override
    public String toStore() {
        String div = " | ";
        return "T" + div + (isDone ? "1" : "0") + div + description;
    }

    /**
     * Return the String represents the task.
     * @return the String represents the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

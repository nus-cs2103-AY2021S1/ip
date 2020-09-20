package duke.task;

/**
 * Represents a Todo, a type of Task, in the Duke program.
 */
public class Todo extends Task {
    protected Integer hours;

    /**
     * Creates a Todo
     * @param description the description of the Todo
     */
    public Todo(String description, Integer hours) {
        super(description);
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (takes: " + hours + " hours)";
    }
}

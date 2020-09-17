package rock.task;

/**
 * A task that need to be done.
 * Command syntax: todo + description
 * Example:        todo get A for CS2103T
 */
public class Todo extends Task {
    /**
     * Constructor.
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor with the addition of isDone.
     * @param isDone Check if the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Represent the task in an audience-friendly form.
     * Format:  [T][isDone] + description
     * Example: [T][x] get A for CS2103T
     */
    @Override
    public String getStatus() {
        return "[T]" + getIcon() + " " + getDescription();
    }

    /**
     * Represent the task in a suitable form to store data.
     * Format:  T|description|isDone
     * Example: T|get A for CS2103T|false
     */
    @Override
    public String getDataFormat() {
        return "T" + "|" + getDescription() + "|" + getIsDone();
    }
}

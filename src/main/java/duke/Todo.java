package duke;

/**
 * A task to do.
 */
public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Todo constructor with specified isDone.
     *
     * @param description the description of the task.
     * @param isDone      specify whether the task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "T | " + super.writeToFile();
    }
}

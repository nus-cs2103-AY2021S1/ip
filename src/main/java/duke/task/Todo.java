package duke.task;

/**
 * Represents a Todo task.
 * @author Tee Kok Siang
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns formatted Todo task information.
     * It will be used to write into the file.
     * @return Formatted Todo task information.
     */
    @Override
    public String toFileString() {
        String done = super.isDone ? "1" : "0";
        return "T | " + done + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
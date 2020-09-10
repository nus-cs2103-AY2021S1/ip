package duke.task;

/**
 * Represents a {@code Task} without any date/time attached to it.
 */

public class Todo extends Task {

    /**
     * Constructor to create a new {@code Todo} object which is initially uncompleted.
     *
     * @param content the content of what the {@code Todo} task is about
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Returns the string representation of the {@code Todo} object which has been formatted to the correct
     * format as the file in which it will be saved into.
     *
     * @return a string representation of the {@code Todo} object which has been formatted accordingly to the
     * file specification which the task will be saved into.
     */
    @Override
    public String toDataFileFormat() {
        return String.format("T | %d | %s | %s", isDone ? 1 : 0, tagName, content);
    }

    /**
     * Returns a string representation of this {@code Todo} object.
     *
     * @return a string representation of the {@code Todo} task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

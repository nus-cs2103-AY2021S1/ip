package duke;

/**
 * Handles todo-based Tasks.
 */

public class Todo extends Task {
    /**
     * Constructor for duke.Todo-type Tasks.
     * @param name Description of duke.Task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for duke.Todo-type Tasks with tags.
     * @param name Description of duke.Task.
     * @param tags Tags associated with Task.
     */
    public Todo(String name, String[] tags) {
        super(name, tags);
    }

    /**
     * Constructor for duke.Todo-type Tasks with completed state.
     * @param name Description of duke.Task.
     * @param isCompleted Completion state of duke.Task.
     */
    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    /**
     * Constructor for duke.Todo-type Tasks with completed state and tags.
     * @param name Description of duke.Task.
     * @param isCompleted Completion state of duke.Task.
     */
    public Todo(String name, boolean isCompleted, String[] tags) {
        super(name, isCompleted, tags);
    }

    /**
     * Represents duke.Todo in format to be saved.
     * @return Saved representation of duke.Todo object.
     */
    @Override
    public String format() {
        return "T" + getDelimiter() + super.format();
    }

    /**
     * Represents duke.Todo in String form.
     * @return String representation of duke.Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

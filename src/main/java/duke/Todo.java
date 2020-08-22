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
     * Constructor for duke.Todo-type Tasks.
     * @param name Description of duke.Task.
     * @param completed Completion state of duke.Task.
     */
    public Todo(String name, boolean completed) {
        super(name, completed);
    }

    /**
     * Represents duke.Todo in format to be saved.
     * @return Saved representation of duke.Todo object.
     */
    @Override
    public String format() {
        return "T" + SAVE_DELIMITER + super.format();
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

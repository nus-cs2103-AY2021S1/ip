/**
 * Handles todo-based Tasks.
 */

public class Todo extends Task {
    /**
     * Constructor for Todo-type Tasks.
     * @param name Description of Task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for Todo-type Tasks.
     * @param name Description of Task.
     * @param completed Completion state of Task.
     */
    public Todo(String name, boolean completed) {
        super(name, completed);
    }

    /**
     * Represents Todo in format to be saved.
     * @return Saved representation of Todo object.
     */
    @Override
    public String format() {
        return "T" + SAVE_DELIMITER + super.format();
    }

    /**
     * Represents Todo in String form.
     * @return String representation of Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

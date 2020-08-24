package duke.tasks;

/** Represents a todo. */
public class Todo extends Task {

    /** Constructs a Todo object with the specified description.
     *
     * @param description The description of this todo.
     */
    public Todo(String description) {
        super(description);
    }

    /** Returns the String representation of this todo in the format it should be saved in the file.
     *
     * @return The String representation of this todo in the appropriate format.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    /** Returns the String representation of this todo to be displayed to the user.
     *
     * @return the String representation of this todo to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

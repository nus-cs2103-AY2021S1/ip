package duke.tasks;

/**
 * Represents a Todo task that is created upon user input.
 */
public class Todo extends Task {
    int code = 0;

    /**
     * Constructor for a Todo task which stores the description
     * of the task.
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints out an error when the format of the Todo is incorrect.
     */
    public static void invalidInput() {
        invalidInput("OOPS!!! The description of a todo cannot be empty.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

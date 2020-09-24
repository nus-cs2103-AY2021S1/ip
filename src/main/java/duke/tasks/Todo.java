package duke.tasks;

/**
 * Represents a Todo task that is created upon user input.
 */
public class Todo extends Task {

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
    public static String invalidInput() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

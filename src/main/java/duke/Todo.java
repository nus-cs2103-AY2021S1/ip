package duke;

/**
 * <h>To-do duke.Task Type</h>
 * This class is a type of tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructor of to-do class
     * @param description Name of the task input by user.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts to-do task to a string to be save in to-do.txt file
     * @return String A string containing task-type, doneStatus, descriptions and details.
     */
    @Override
    public String convertToText() {
        return "T" + super.convertToText();
    }

    /**
     * Overridden toString method to output name, type and status of task.
     * @return String This returns a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

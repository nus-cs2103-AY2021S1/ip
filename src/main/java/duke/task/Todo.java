package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates methods and information that relate to a todo task.
 */
public class Todo extends Task {

    /**
     * Creates and initialises a new Todo object.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo object into a string for storage in a file.
     *
     * @return String containing the relevant information of this todo object to be saved in a file.
     */
    @Override
    public String convertTaskToFileString() {
        return "T | " + (hasBeenCompleted() ? "1 | " : "0 | ")
                + getDescription() + " | " + this.getTaskTag();
    }

    /**
     * Returns the date of this todo task.
     *
     * @return Null since a todo task has no relevant date information.
     */
    @Override
    public LocalDate getDate() {
        return null;
    }

    /**
     * Converts the todo object into a string to be displayed.
     *
     * @return String representation of this todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

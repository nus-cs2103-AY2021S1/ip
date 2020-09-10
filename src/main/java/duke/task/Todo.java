package duke.task;

import java.time.LocalDate;

import java.util.Optional;

/**
 * Represents a To-do Task.
 */
public class Todo extends Task {

    /**
     * Constructor of a To-Do task with description and task status indicating whether it is
     * completed.
     *
     * @param description Description of To-Do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A method to display To-do object attributes in String format.
     *
     * @return To-Do task attributes in a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * A method to display To-do object attributes in String format for the save file.
     *
     * @return To-Do task attributes in a string for the save file.
     */
    @Override
    public String toFile() {
        return "T | " + getStatusCode() + " | " + description;
    }


    /**
     * Todo object does not require the user to input date.
     * Thus, it returns an empty Optional instance.
     *
     * @return Java Optional instance that is empty.
     */
    @Override
    public Optional<LocalDate> getDate() {
        return Optional.empty();
    }
}

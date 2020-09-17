package duke.task;

/**
 * Class that simulates the todo task that user has inputted.
 */
public class ToDo extends Task {

    /**
     * Initialize a todo object the containing details of the task.
     *
     * @param description Details of the task.
     */
    public ToDo(String description) {
        super(description, 1);
    }

    /**
     * Initialize a todo object the containing details of the task.
     *
     * @param description Details of the task.
     * @param isDone Boolean value of whether a task is completed.
     * @param isReminderOn Boolean value of whether a task is set on reminder.
     */
    public ToDo(String description, boolean isDone, boolean isReminderOn) {
        super(description, isDone, isReminderOn, 1);
    }


    /**
     * Return a proper styling to be recorded into CSV.
     *
     * @return A formatted string to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("todo%s", super.formatStyling());
    }

    /**
     * A String representation of the todo object.
     *
     * @return A String representation of the todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

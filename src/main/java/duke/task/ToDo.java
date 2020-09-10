package duke.task;

/**
 * Class that simulates the todo task that user has inputted.
 */

public class ToDo extends Task {
    /**
     * Creates a todo object the containing details of the task.
     *
     * @param description Details of the task.
     */
    public ToDo(String description) {
        super(description, 1);
    }

    /**
     * Creates a todo object the containing details of the task.
     *
     * @param description Details of the task.
     * @param isDone Boolean value of whether a task is completed.
     */
    public ToDo(String description, boolean isDone, boolean isReminderOn) {
        super(description, isDone, isReminderOn, 1);
    }


    /**
     * Returns a proper styling to be recorded into CSV.
     *
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("todo%s", super.formatStyling());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

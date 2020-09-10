package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task that can be added to Duke's Task List.
 */
public class Task {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter PRINT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private String description;
    private boolean isDone;

    /**
     * Constructs the task with given description.
     *
     * @param description  String of the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor that constructs the task with done status.
     *
     * @param description  String of the description of the task.
     * @param isDone  Whether the task is already done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks itself as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task.
     *
     * @return  Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * String representation of the task.
     *
     * @return  String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + this.description;
    }

    /**
     * String representation of the task to be written to a file.
     *
     * @return  String representation of the task to be written to a file.
     */
    public String getFileSaveText() {
        return "| " + (isDone ? "1" : "0") + " | " + this.description;
    }
}

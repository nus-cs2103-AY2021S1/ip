package duke.tasks;

import duke.types.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates a task that the user adds in.
 */
public class Task {
    private final String content;
    private boolean completed = false;
    private TaskType type;
    private LocalDate date;

    /**
     * Constructs a task object that has no date attached.
     *
     * @param content the content of the task
     * @param type the type of the task
     */
    public Task(String content, String type) {
        this.content = content;
        this.type = TaskType.valueOfType(type);
    }

    /**
     * Constructs a task object that has a date attached.
     *
     * @param content the content of the task.
     * @param type the type of the task.
     * @param date the date of the task.
     */
    public Task(String content, String type, String date) {
        this.content = content;
        this.type = TaskType.valueOfType(type);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs a task object that has a date attached.
     *
     * @param content the content of the task.
     * @param type the type of the task.
     * @param date a LocalDate object representing the date of the task.
     */
    public Task(String content, String type, LocalDate date) {
        this.content = content;
        this.type = TaskType.valueOfType(type);
        this.date = date;
    }

    /**
     * Gets the content of the task.
     *
     * @return a string representing the content of the task.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets whether the task has been completed.
     *
     * @return true if the task is completed and false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Gets the type of the task.
     *
     * @return a string representing the type of the task.
     */
    public String getType() {
        return type.getType();
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        completed = true;
    }

    /**
     * Gets the date of the task in the format of Month date Year.
     *
     * @return a string representing the date of the task in "MMM d yyyy".
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Overrides the toString method to represent the task in the form [T][✓] content (on/at: date).
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        String statusLabel = "[" + type.toString().substring(0, 1) + "]";
        String mainBody = statusLabel + (completed ? "[✓]" : "[✗]") + " " + content;
        if (type == TaskType.EVENT) {
            mainBody += " (on: " + getDate() + ")";
        }
        if (type == TaskType.DEADLINE) {
            mainBody += " (by: " + getDate() + ")";
        }
        return mainBody;
    }
}

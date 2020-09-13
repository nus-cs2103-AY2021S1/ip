package duke.task;

import static duke.util.Keyword.KEYWORD_CROSS;
import static duke.util.Keyword.KEYWORD_TASK_MARKED;
import static duke.util.Keyword.KEYWORD_TASK_MARKED_BEFORE;
import static duke.util.Keyword.KEYWORD_TASK_REMINDER_OFF;
import static duke.util.Keyword.KEYWORD_TASK_REMINDER_ON;
import static duke.util.Keyword.KEYWORD_TICK;

import java.time.LocalDateTime;

/**
 * Class that simulates the task that user has inputted.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected boolean isReminderOn;
    protected int priority;
    protected LocalDateTime dueDate;

    /**
     * Initialize a task object the containing details of the task.
     *
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isReminderOn = false;
    }

    /**
     * Initialize a task object the containing details of the task.
     *
     * @param description Details of the task.
     * @param isDone Boolean value representing whether a task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isReminderOn = false;
    }

    /**
     * Initialize a task object the containing details of the task.
     *
     * @param description Details of the task.
     * @param priority Integer value denoting the priority of the task.
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.isReminderOn = false;
        this.priority = priority;
    }

    /**
     * Initialize a task object the containing details of the task.
     *
     * @param description Details of the task.
     * @param priority Integer value denoting the priority of the task.
     * @param dueDate LocalDateTime object denoting the due date of the task.
     */
    public Task(String description, int priority, LocalDateTime dueDate) {
        this.dueDate = dueDate;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Initialize a task object the containing details of the task.
     *
     * @param description Details of the task.
     * @param isDone Boolean value of whether a task is completed.
     * @param isReminderOn Boolean value of whether this task needs a reminder.
     * @param priority Integer value denoting the priority of the task.
     */
    public Task(String description, boolean isDone, boolean isReminderOn, int priority) {
        this.isDone = isDone;
        this.description = description;
        this.isReminderOn = isReminderOn;
        this.priority = priority;
    }

    /**
     * Initialize a task object the containing details of the task.
     *
     * @param description Details of the task.
     * @param isDone Boolean value of whether a task is completed.
     * @param isReminderOn Boolean value of whether this task needs a reminder.
     * @param priority Integer value denoting the priority of the task.
     * @param dueDate LocalDateTime object denoting the due date of the task.
     */
    public Task(String description, boolean isDone, boolean isReminderOn, int priority, LocalDateTime dueDate) {
        this.isDone = isDone;
        this.description = description;
        this.isReminderOn = isReminderOn;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    /**
     * Display a cross if task is not done, a tick otherwise.
     *
     * @return Byte encoding strings of the symbols tick or X.
     */
    private String getStatusIcon() {
        return (isDone ? KEYWORD_TICK : KEYWORD_CROSS);
    }

    /**
     * Mark the task as completed.
     */
    public String markAsDone() {
        if (this.isDone) {
            return KEYWORD_TASK_MARKED_BEFORE;
        } else {
            this.isDone = true;
            return KEYWORD_TASK_MARKED + "\n" + this.toString();
        }
    }

    /**
     * Set the status of reminder option.
     *
     * @param status boolean value denote the status of reminder option.
     */
    public String setReminder(boolean status) {
        isReminderOn = status;
        if (status) {
            return String.format(KEYWORD_TASK_REMINDER_ON, description);
        } else {
            return String.format(KEYWORD_TASK_REMINDER_OFF, description);
        }
    }

    /**
     * Return a proper styling to be recorded into CSV.
     *
     * @return A formatted string to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format(",%s,%d,%d\n", description, getTaskStatus(), getReminderStatus());
    }

    /**
     * If a task has a reminder on, return 1 else 0.
     *
     * @return An integer coded for the reminder status of the task.
     */
    public int getReminderStatus() {
        return isReminderOn ? 1 : 0;
    }

    /**
     * If a task is completed, return 1 else 0.
     *
     * @return An integer coded for the completed status of the task.
     */
    public int getTaskStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Retrieve the details of the task.
     *
     * @return A string representing the details of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieve the priority of the task.
     *
     * @return Integer representing the priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Retrieve the due date of the task.
     *
     * @return A LocalDateTime object representing the due date.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * A String representation of the task object.
     *
     * @return A String representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

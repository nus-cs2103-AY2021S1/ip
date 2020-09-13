package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores all methods and properties of a Task class.
 * Variables include description, task type, time and done status.
 */
public abstract class Task {

    private static final String dateTimeFormat = "MMM d yyyy / h.mm a";

    private final String description;
    private final TaskType taskType;
    private final LocalDateTime dateTime;
    private final String timeFrame;

    private boolean isDone;

    /**
     * Initialises the Task object.
     *
     * @param description Task details.
     * @param isDone Status of task - true if done, false otherwise.
     * @param taskType Type of Task.
     * @param dateTime Date and time of the task.
     */
    protected Task(String description, TaskType taskType, String timeFrame, LocalDateTime dateTime, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.dateTime = dateTime;
        this.timeFrame = timeFrame;
        this.isDone = isDone;
    }

    /**
     * Obtains the Done and Not done icons.
     *
     * @return String representation of icons.
     */
    private String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Retrieves the status of the Task.
     *
     * @return True iff task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieves the Task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the name of task.
     *
     * @return Task name in CAPS.
     */
    public String getTaskName() {
        return taskType.toString();
    }

    /**
     * Retrieves the String representation of the status of Task.
     *
     * @return String representation of Task status.
     */
    public String getStatus() {
        return isDone ? "Done" : "Not done";
    }

    /**
     * Retrieves the time of the Task.
     *
     * @return Time of Task.
     */
    public String getTime() {
        return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    /**
     * Returns true iff the task description contains the query word, false otherwise.
     *
     * @param query Query word.
     * @return true iff task description contains the query.
     */
    public boolean match(String query) {
        return description.contains(query);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

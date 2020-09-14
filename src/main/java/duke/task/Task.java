package duke.task;

import static duke.util.Keyword.CROSS_SYMBOL;
import static duke.util.Keyword.DATE_TIME_FORMAT;
import static duke.util.Keyword.DONE;
import static duke.util.Keyword.NOT_DONE;
import static duke.util.Keyword.SINGLE_SPACE;
import static duke.util.Keyword.TICK_SYMBOL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores all methods and properties of a Task class.
 * Variables include description, task type, time frame, and dateTime and done status.
 */
public abstract class Task implements Comparable<Task> {

    private final String description;
    private final TaskType taskType;
    private final LocalDateTime dateTime;
    private final String timeFrame;

    private boolean isDone;

    /**
     * Initialises the Task object.
     *
     * @param description Task details.
     * @param taskType Type of Task.
     * @param timeFrame Time frame of Task (Only valid for Event).
     * @param dateTime Date and time of the task (Deadline for Deadline objects, creation time for other tasks).
     * @param isDone Status of task - true if done, false otherwise.
     */
    protected Task(String description, TaskType taskType, String timeFrame, LocalDateTime dateTime, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.dateTime = dateTime;
        this.timeFrame = timeFrame;
        this.isDone = isDone;
    }

    /**
     * Obtains the Done and Not done symbols.
     *
     * @return String representation of symbols.
     */
    private String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
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
        return isDone ? DONE : NOT_DONE;
    }

    /**
     * Retrieves the time of the Task.
     *
     * @return Time of Task.
     */
    public String getTime() {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    /**
     * Gets the time frame of the task.
     *
     * @return Time frame of task.
     */
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
        return getStatusIcon() + SINGLE_SPACE + description;
    }

    /**
     * Compares each task according to their {@code LocalDateTime} dateTime.
     *
     * @param otherTask Other task to compare to.
     * @return The comparator value, negative if less, positive if greater.
     */
    @Override
    public int compareTo(Task otherTask) {
        return this.dateTime.compareTo(otherTask.dateTime);
    }
}

package butler.task;

import java.time.LocalDate;

/**
 * Represents a task occurring over a period of time.
 * This task has a starting date and ending date.
 */
public class EventTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an event task with the given <code>summary</code>,
     * <code>startDate</code> and <code>endDate</code>.
     *
     * @param summary Summary of this task.
     * @param startDate Starting date of this task.
     * @param endDate Ending date of this task.
     */
    public EventTask(String summary, LocalDate startDate, LocalDate endDate) {
        super(summary);
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskType = TaskType.EVENT;
    }

    /**
     * Gets the time period of this event task in human readable format.
     *
     * @return Time period of this event task.
     */
    public String getEventPeriod() {
        return startDate.getDayOfMonth() + " "
                + startDate.getMonth().toString() + " "
                + startDate.getYear() + " to "
                + endDate.getDayOfMonth() + " "
                + endDate.getMonth().toString() + " "
                + endDate.getYear();
    }

    /**
     * Returns a string representation of this event task.
     *
     * @return String representation of this event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + getEventPeriod() + ")";
    }

    /**
     * Returns a string representation of this task for storage in hard disk.
     *
     * @return String representation of this task for storage in hard disk.
     */
    @Override
    public String toStorageString() {
        return super.toStorageString()
                + " /at " + startDate + " " + endDate;
    }

    /**
     * Returns a deep copy of this task.
     *
     * @return A deep copy of this task.
     */
    @Override
    public Task copy() {
        EventTask task = new EventTask(summary, startDate, endDate);
        task.isComplete = this.isComplete;
        return task;
    }
}

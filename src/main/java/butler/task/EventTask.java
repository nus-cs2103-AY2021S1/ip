package butler.task;

import java.time.LocalDate;

/**
 * Represents a task occurring over a period of time.
 * This task has a starting date and ending date.
 */
public class EventTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

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
    private String getEventDuration() {
        return startDate.getDayOfMonth() + " "
                + startDate.getMonth().toString() + " "
                + startDate.getYear() + " to "
                + endDate.getDayOfMonth() + " "
                + endDate.getMonth().toString() + " "
                + endDate.getYear();
    }

    /**
     * Gets the starting date of this event task.
     *
     * @return Starting date of this event task.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the ending date of this event task.
     *
     * @return Ending date of this event task.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns a string representation of this event task.
     *
     * @return String representation of this event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getEventDuration() + ")";
    }
}

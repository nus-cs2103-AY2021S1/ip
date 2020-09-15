package viscount.task;

import java.time.LocalDateTime;

import viscount.Parser;

/**
 * Represents an event, a type of task.
 */
public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s|%s";

    /**
     * Events have an additional event time field.
     */
    private LocalDateTime eventTime;

    /**
     * Instantiates a new event task.
     *
     * @param description Description of event added.
     * @param isDone Represents if event is done.
     * @param eventTime Date and time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime eventTime) {
        super(TaskType.EVENT, description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns whether the task has a date time field.
     *
     * @return True as events have a date time field.
     */
    @Override
    public boolean hasDateTime() {
        return true;
    }

    /**
     * Gets the event time of the event.
     *
     * @return Event time of the event.
     */
    @Override
    public LocalDateTime getDateTime() {
        return eventTime;
    }

    /**
     * Sets the event time of the event.
     *
     * @param newDateTime New event time of the event.
     */
    @Override
    public void setDateTime(LocalDateTime newDateTime) {
        this.eventTime = newDateTime;
    }

    /**
     * Gives a task data representation of the task in String format.
     *
     * @return Task data representation of the task.
     */
    @Override
    public String toTaskData() {
        return String.format(Event.TASK_DATA_FORMAT, taskType.name(), isDone ? 1 : 0, description,
                eventTime.format(Parser.TASK_DATA_DATE_TIME_FORMATTER));
    }

    /**
     * Gives a displayable string representation of the task.
     *
     * @return Displayable string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description,
                eventTime.format(Parser.OUTPUT_DATE_TIME_FORMATTER));
    }

    /**
     * Compares this task with another object for equality.
     *
     * @param o Object compared.
     * @return True if this task is equal to the object, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Event event = (Event) o;
        boolean hasSameDescription = this.description.equals(event.description);
        boolean hasSameDone = this.isDone == event.isDone;
        boolean hasSameEventTime = this.eventTime.isEqual(event.eventTime);
        return hasSameDescription && hasSameDone && hasSameEventTime;
    }
}

package cartona.task;

/**
 * The Event class is a Task that represents an event, with a start date and an end date - represented by TaskDates.
 *
 * @author Jaya Rengam
 */
public class Event extends Task {
    private TaskDate start;
    private TaskDate end;

    /**
     * Creates an Event object.
     *
     * @param name the name of the Event
     * @param isDone whether the Event has been completed or not
     * @param start the starting date and time of the Event
     * @param end the ending date and time of the Event
     */
    public Event(String name, boolean isDone, TaskDate start, TaskDate end) {
        super(name, isDone, "E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(), this.start, this.end.getTime());
    }

    @Override
    public String getAbbreviatedString() {
        int isDoneRep = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s - %s", this.type, isDoneRep, this.name,
                                this.start.toString(), this.end.getTime());
    }

    /**
     * Returns the start date of the Event.
     */
    public TaskDate getStartDate() {
        return this.start;
    }

    /**
     * Returns the end date of the Event.
     */
    public TaskDate getEndDate() {
        return this.end;
    }
}

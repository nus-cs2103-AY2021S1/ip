package duke.core.task;

import duke.core.util.DukeDateTime;
import duke.core.storage.CsvToTask;

import java.util.Objects;

/**
 * A Task with a startTime, and an endTime
 */
public class Event extends Task {

    private DukeDateTime start;
    private DukeDateTime end;

    /**
     * Create an undone Event with the specified description, start datetime and end datetime
     * @param description A description of the Event
     * @param start The start datetime of the event
     * @param end The end datetime of the event
     */
    public Event(String description, DukeDateTime start, DukeDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Create an Event with the specified description, start datetime and end datetime,
     * and additionally specifying the completion status of the Event
     * @param isCompleted A boolean to indicate the completion status of the task
     * @param description A description of the Event
     * @param start The start datetime of the event
     * @param end The end datetime of the event
     */
    public Event(boolean isCompleted, String description, DukeDateTime start, DukeDateTime end) {
        super(isCompleted, description);
        this.start = start;
        this.end = end;
    }

    /**
     * @return The start datetime of this event
     */
    public DukeDateTime getStart() {
        return this.start;
    }

    /**
     * @param start Set the start datetime of this event
     */
    public void setStart(DukeDateTime start) {
        this.start = start;
    }

    /**
     * @return The end datetime of this Event
     */
    public DukeDateTime getEnd() {
        return this.end;
    }

    /**
     * @param end Set the end datetime of this event
     */
    public void setEnd(DukeDateTime end) {
        this.end = end;
    }

    /**
     * @return A csv representation of this task.
     */
    @Override
    public String toCsv() {
        return CsvToTask.EVENT + ","
                + this.isCompleted() + ","
                + this.getDescription() + ","
                + this.getStart() + ","
                + this.getEnd();
    }

    /**
     * @return A readable text representation of this task.
     */
    @Override
    public String toString() {
        return "[E]"
                + "[" + this.isCompletedSymbol() + "]"
                + " " + this.getDescription()
                + " (from: " + start.pretty()
                + " till: " + end.pretty() + ")";
    }

    /**
     * Two Events are equivalent if they have the same description,
     * have the same completion status, and have the same start and end time
     * @param obj The object to compare this object to
     * @return true if they are equivalent. Otherwise, false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Event event = (Event) obj;
        return start.equals(event.start)
                && end.equals(event.end);
    }

    /**
     * @return A hashcode of the description, completion status,
     * event start datetime, and event end datetime
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), start, end);
    }
}

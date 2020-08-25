package duke.task;

import duke.DukeDateTime;

import java.util.Objects;

/**
 * A Task with a startTime, and an endTime
 */
public class Event extends Task {

    private final DukeDateTime eventStart;
    private final DukeDateTime eventEnd;

    public Event(String description, DukeDateTime eventStart, DukeDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public Event(boolean isCompleted, String description, DukeDateTime eventStart, DukeDateTime eventEnd) {
        super(isCompleted, description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public DukeDateTime getStart() {
        return this.eventStart;
    }

    public DukeDateTime getEnd() {
        return this.eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.pretty() + " till: " + eventEnd.pretty() + ")";
    }

    /**
     * Get the csv representation of this task
     * @return A csv String representative of this task
     */
    @Override
    public String toCsv() {
        return TaskFactory.EVENT + "," + super.toCsv() + "," + eventStart + "," + eventEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return eventStart.equals(event.eventStart) &&
                eventEnd.equals(event.eventEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eventStart, eventEnd);
    }
}

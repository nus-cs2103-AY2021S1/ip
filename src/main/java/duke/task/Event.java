package duke.task;

import duke.util.DukeDateTime;
import duke.storage.CsvToTask;

import java.util.Objects;

/**
 * A Task with a startTime, and an endTime
 */
public class Event extends Task {

    private DukeDateTime start;
    private DukeDateTime end;

    public Event(String description, DukeDateTime start, DukeDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(boolean isCompleted, String description, DukeDateTime start, DukeDateTime end) {
        super(isCompleted, description);
        this.start = start;
        this.end = end;
    }

    public DukeDateTime getStart() {
        return this.start;
    }

    public void setStart(DukeDateTime eventStart) {
        this.start = eventStart;
    }

    public DukeDateTime getEnd() {
        return this.end;
    }

    public void setEnd(DukeDateTime eventEnd) {
        this.end = eventEnd;
    }

    @Override
    public String toCsv() {
        return CsvToTask.EVENT + ","
                + this.isCompleted() + ","
                + this.getDescription() + ","
                + this.getStart() + ","
                + this.getEnd();
    }

    @Override
    public String toString() {
        return "[E]"
                + "[" + this.isCompletedSymbol() + "]"
                + " " + this.getDescription()
                + " (from: " + start.pretty()
                + " till: " + end.pretty() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Event)) return false;
        if (!super.equals(obj)) return false;
        Event event = (Event) obj;
        return start.equals(event.start) &&
                end.equals(event.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), start, end);
    }
}

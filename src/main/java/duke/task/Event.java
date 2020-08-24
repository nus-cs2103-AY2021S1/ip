package duke.task;

import duke.DukeDateTime;

import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * A Task with a startTime, and an endTime
 */
public class Event extends Task{

    private final DukeDateTime eventStart;
    private final DukeDateTime eventEnd;

    public Event(String description, DukeDateTime eventStart, DukeDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public Event(boolean completed, String description, DukeDateTime eventStart, DukeDateTime eventEnd) {
        super(completed, description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public LocalDateTime getStart() {
        return this.eventStart.get();
    }

    public LocalDateTime getEnd() {
        return this.eventEnd.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Event)) return false;
        if (!super.equals(obj)) return false;
        Event event = (Event) obj;
        return getStart().equals(event.getStart()) &&
                getEnd().equals(event.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStart(), getEnd());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.pretty() + " till: " + eventEnd.pretty() + ")";
    }

    @Override
    public String toCSV() {
        return "E," + super.toCSV() + "," + eventStart + "," + eventEnd;
    }

    // Warning: does not check for corrupt entry
    public static Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct duke.task from csv
        return new Event(
                Boolean.parseBoolean(scanner.next()),
                scanner.next(),
                new DukeDateTime(scanner.next()),
                new DukeDateTime(scanner.next())
        );
    }

}

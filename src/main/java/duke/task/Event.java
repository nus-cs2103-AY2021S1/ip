package duke.task;

import duke.DukeDateTime;

import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;

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
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.pretty() + " till: " + eventEnd.pretty() + ")";
    }

    /**
     * Get the csv representation of this task
     * @return A csv String representative of this task
     */
    @Override
    public String toCsv() {
        return TaskEnum.EVENT + "," + super.toCsv() + "," + eventStart + "," + eventEnd;
    }

    /**
     * Initialize an event instance from it's csv representation
     * @param csv An event in csv format
     * @return The event represented by the csv
     * @throws Exception If csv cannot be parsed into a event object
     */
    public static Task fromCsv(String csv) throws Exception{
        Scanner scanner = new Scanner(csv);
        scanner.useDelimiter(",");
        scanner.next(); // Discard first match

        // Construct task from csv
        return new Event(
                Boolean.parseBoolean(scanner.next()),
                scanner.next(),
                new DukeDateTime(scanner.next()),
                new DukeDateTime(scanner.next())
        );
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

}

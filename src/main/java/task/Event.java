package task;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

/**
 * An Event is a Task with startTime, and endTime
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
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.pretty() + " till: " + eventEnd.pretty() + ")";
    }

    @Override
    public String toCSV() {
        return "E," + super.toCSV() + "," + eventStart + "," + eventEnd;
    }

    @Override
    public Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        Pattern pattern = Pattern.compile("([^,]+?),");
        return new Event(
                scanner.nextBoolean(),
                scanner.next(pattern),
                DukeDateTime.generate(scanner.next(pattern)),
                DukeDateTime.generate(scanner.next(pattern))
        );
    }

}

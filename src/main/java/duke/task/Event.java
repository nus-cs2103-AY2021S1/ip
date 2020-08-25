package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;

    public Event(String description, String at) {
        super(description);
        String[] startEnd = at.split(" to ");
        LocalDateTime timeStart;
        LocalDateTime timeEnd;
        try {
            timeStart = LocalDateTime.parse(startEnd[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            timeEnd = LocalDateTime.parse(startEnd[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException ignored) {
            return;
        }
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        String at = timeStart.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        String at = timeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E" + separator + isDone + separator + super.description + separator + at + "\n";
    }
}

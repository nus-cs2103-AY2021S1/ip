package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime time;

    public Deadline(String description, String by) {
        super(description);
        LocalDateTime timeBy;
        try {
            timeBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException ignored) {
            return;
        }
        this.time = timeBy;
    }

    @Override
    public String toString() {
        String by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        String by = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D" + separator + isDone + separator + super.description + separator + by + "\n";
    }
}
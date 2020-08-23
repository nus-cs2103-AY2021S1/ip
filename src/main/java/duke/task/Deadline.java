package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime time;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        LocalDateTime timeBy;
        try {
            timeBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        } catch (DateTimeParseException ex) {
            throw new DukeException("duke.task.Deadline timing cannot be parsed");
        }
        this.time = timeBy;
    }

    @Override
    public String toString() {
        String by = time.format(DateTimeFormatter.ofPattern("MMM d yyyy kk:mm"));
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        String by = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        return "D" + separator + isDone + separator + super.description + separator + by + "\n";
    }
}
package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {

    private String time;
    private LocalDate date;
    private LocalDateTime dateTime;

    /**
     * Constructor for a Deadline Task.
     */
    public Deadline(String description, String time) throws DukeException {
        super(description);
        try {
            this.time = time;
            String[] parts = time.split(" ");
            String[] timeParts = parts[1].split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("d/MM/yyyy"));
            dateTime = date.atTime(hour, minute);
        } catch (Exception e) {
             throw new DukeException("create deadline object failed");
        }
    }

    /**
     * Constructor for a Deadline Task.
     */
    public Deadline(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
        String[] parts = time.split(" ");
        String[] timeParts = parts[1].split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("d/MM/yyyy"));
        dateTime = date.atTime(hour, minute);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    @Override
    public String toStoreFormat() {
        String type = "D";
        String status = super.isDone ? "1" : "0";
        String connect = " | ";
        return type + connect + status + connect + description + connect + time;
    }
}

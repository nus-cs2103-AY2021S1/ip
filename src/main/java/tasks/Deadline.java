package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a name and a LocalDateTime object.
 */

public class Deadline extends Task {
    protected LocalDateTime datetime;

    public Deadline(String name, LocalDateTime datetime, boolean done) {
        super(name, done);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String writeString() {
        return "D # " + super.writeString() + " # " + datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}

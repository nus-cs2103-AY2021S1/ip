package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = formatter.format(this.date);
        return String.format("[D]%s (by: %s)", super.toString(), date);
    }
}

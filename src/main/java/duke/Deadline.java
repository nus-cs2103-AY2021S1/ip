package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getTaskName() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}

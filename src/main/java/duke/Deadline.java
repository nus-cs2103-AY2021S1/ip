package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends duke.Task {

    private LocalDate date;

    public Deadline(String name, String dateTime) {
        super(name);
        this.date = LocalDate.parse(dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getItemName() + "(by: " +
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}

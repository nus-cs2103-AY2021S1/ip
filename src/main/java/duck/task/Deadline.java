package duck.task;

import java.time.LocalDate;

/**
 * Deadline class for representing the deadline type.
 */
public class Deadline extends TaskWithDate {

    public Deadline(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + super.getDateAsString() + ")";
    }
}

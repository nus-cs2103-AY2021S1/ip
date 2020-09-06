package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class for task of deadline type.
 */
public class Deadline extends Task {

    /**A format date/time object to store the deadline of an event */
    protected LocalDateTime by;

    /**
     * Public constructor for a deadline task.
     *
     * Requires an description and a time of the
     * deadline of LocalDateTime form.
     * @param description Describes the deadline task
     * @param by Date/time of the deadline task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String getNotification() {
        return this.description + "@" + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    @Override
    public boolean isComing(int days) {
        return by.minusDays(days).isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }
}

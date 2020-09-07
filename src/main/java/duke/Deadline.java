package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * This constructor creates a Deadline task instance, invoking the super class Task's constructor
     * @param description the String description of the deadline task
     * @param date the date where the task must be completed by. It is a type of LocalDateTime.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public Optional<LocalDateTime> getLocalDateTime() {
        return Optional.of(this.date);
    }

    private String formatDate() {
        assert date != null;
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]"
                + "[" + getStatusIcon() + "]"
                + description + " "
                + "(by: " + formatDate() + ")";
    }
}

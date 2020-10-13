package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    private LocalDateTime date;

    /**
     * Constructor instantiates event task, uses parent class Task's constructor
     * @param description the String description of the event task
     * @param date the String date where event is occurring
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "E";
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
                + "(at: " + formatDate() + ")";
    }
}

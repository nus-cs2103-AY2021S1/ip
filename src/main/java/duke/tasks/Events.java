package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime start;

    public Events(String description, LocalDateTime startTime) {
        super(description);
        this.start = startTime;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDate getDate() {
        return start.toLocalDate();
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH : mm")) + ")";
    }
}

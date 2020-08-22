package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime startTime;

    public Events(String description, LocalDateTime startTime) {
        super(description);
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDate getDate() {
        return startTime.toLocalDate();
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH : mm")) + ")";
    }
}

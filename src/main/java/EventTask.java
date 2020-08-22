package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime date;
    EventTask(String taskName, LocalDateTime time) {
        super(taskName);
        this.date = time;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}

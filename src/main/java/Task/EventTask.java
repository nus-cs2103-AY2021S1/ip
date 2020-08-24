package main.java.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    public static final String DEADLINE = "[E]";
    private LocalDateTime dateTime;

    public EventTask() {}
    public EventTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = date;
    }

    public String getType() {
        return DEADLINE;
    }


    @Override
    public String toString() {
        return DEADLINE + super.toString() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyy, h a")) + ")";
    }
}
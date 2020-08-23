package main.java;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    public static final String DEADLINE = "[D]";
    private LocalDateTime dateTime;

    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateTime = date;
    }

    @Override
    public String toString() {
        return DEADLINE + super.toString() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyy, h a")) + ")";
    }
}

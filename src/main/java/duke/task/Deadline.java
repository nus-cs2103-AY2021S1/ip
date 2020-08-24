package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected String by;

    public String getBy() {
        return by;
    }

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        this.by = convertDateAndTimeToString();
    }

    // Date time format is dd/MM/yyyy tttt
    String convertDateAndTimeToString() {
        String str = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + time;
        return str;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public LocalTime getLocalTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +  " (by: " + by + ")";
    }
}

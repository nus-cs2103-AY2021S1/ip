package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;


public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public Event(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }

    @Override
    public String toSavedString() {
        return "E" + " | " + super.toSavedString() + " | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

}

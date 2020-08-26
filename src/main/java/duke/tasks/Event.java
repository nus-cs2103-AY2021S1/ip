package main.java.duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getState() {
        return "E|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.date.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a")) + ")";
    }
}

package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime date;

    public Event(String description, String date) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = LocalDateTime.parse(date, formatter);
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

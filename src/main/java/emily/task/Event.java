package main.java.emily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate at;

    public Event(String description, String str) {
        super(description);
        this.at = LocalDate.parse(str);
        this.type = "E";
    }

    public LocalDate getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

package main.java.emily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;
    protected String timeStamp;

    public Event(String description, String timeStamp) {
        super(description);
        this.timeStamp = timeStamp;
        this.at = LocalDate.parse(timeStamp);
        this.type = "E";
    }

    public String getAt() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

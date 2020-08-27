package main.java.emily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task which has a timestamp at a given time
 * and description
 */
public class Event extends Task{

    protected LocalDate at;

    public Event(String description, String str) {
        super(description);
        this.at = LocalDate.parse(str);
        this.type = 'E';
    }

    /**
     * Getter method to retrieve timestamp
     * @return String of the date in the form yyy-mm-dd
     */
    public LocalDate getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

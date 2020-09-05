package main.java.emily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which has a timestamp by a given date
 * and a description detail
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected String timeStamp;

    /**
     * Subclass of Task with timestamp
     *
     * @param description String of the task name
     * @param timeStamp   String of the time
     */
    public Deadline(String description, String timeStamp) {
        super(description);
        this.timeStamp = timeStamp;
        this.by = LocalDate.parse(timeStamp);
    }

    /**
     * Getter method to retrieve timestamp
     *
     * @return timestamp in the format yyy-mm--dd
     */
    public String getBy() {
        return this.timeStamp;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by + ")";
    }

}
package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates a type of task with timing
     *
     * @param description detail of the task
     * @param at          time in yyyy-mm-dd
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at.trim());
    }


    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDate.parse(at.trim());
    }


    /**
     * Overrides the toString method
     *
     * @return a custom event description
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyy")) + ")";
    }


    // D | 0 | return book | June 6th
    public String toCustomString() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + at;
    }


}
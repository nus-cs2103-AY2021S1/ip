package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class acts as a task that occurs on a specific day.
 * It extends the parent class Task and has a LocalDate at to represent the specified date
 */
public class Event extends Task {

    public LocalDate at;


    /**
     * Constructor for new event
     * @param description   the description of the event
     * @param at            the specified date for the event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for loading deadlines
     * @param description   the description of the event
     * @param at            the specified date for the event
     * @param done          specifies whether the event is completed
     */
    public Event(String done, String description, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = (done == "1");
    }

    /**
     * Display event
     * @return event in a string format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * How event is written into the text file
     * @return event in a string format
     */
    @Override
    public String splitToString() {
        return "\n" + "E" + "/" + this.ifDone() + "/" + this.description + "/" + this.at;
    }
}
package duke.tasks;

import java.time.LocalDate;

/**
 * Represents an Event task that is created upon user input.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for an Event Task which stores the description
     * of the task and the specific timing of it.
     * @param description Task description.
     * @param at Task timing.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(this.at);
    }

    /**
     * Getter for the specific timing of the Event.
     * @return Task timing.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Prints out an error when the format of the deadline is incorrect.
     */
    public static String invalidInput() {
        return "OOPS!!! The format of the Event is wrong.";
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateFormatted() + ")";
    }
}

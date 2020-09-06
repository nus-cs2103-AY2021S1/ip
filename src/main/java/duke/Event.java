package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h>duke.Event duke.Task Type</h>
 * This is a type of tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor of duke.Event class.
     * @param description Name of the task input by user.
     * @param at Time of the task input by user.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Getter for the time of the task.
     * @return LocalDate Date of the time of the duke.Event task.
     */
    public LocalDate getAt() {
        return at;
    }

    /**
     * Converts event task to a string to be save in to-do.txt file
     * @return String A string containing task-type, doneStatus, descriptions and details.
     */
    @Override
    public String convertToText() {
        String str;
        String link = " >> ";
        if (!this.isDone()) {
            str = "E" + link + "0" + link + this.getDescription() + link
                    + this.getAt().toString();
        } else {
            str = "E" + link + "1" + link + this.getDescription() + link
                    + this.getAt().toString();
        }
        return str;
    }

    /**
     * Overridden toString method to output name, type and status of task.
     * @return String This returns a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

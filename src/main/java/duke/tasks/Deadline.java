package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a Deadline task that is created upon user input.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline Task which stores the description of
     * the task and the deadline of it.
     * @param description Task description.
     * @param by Task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(this.by);
    }

    /**
     * Prints out an error when the format of the deadline is incorrect.
     */
    public static String invalidInput() {
        return "OOPS!!! The format of the Deadline is wrong.";
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateFormatted() + ")";
    }

}

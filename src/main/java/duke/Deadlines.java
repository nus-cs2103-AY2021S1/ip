package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines class will handle tasks categorised as deadline.
 */
public class Deadlines extends Task {

    /** Deadline in LocalDate format */
    protected LocalDate deadline;
    /** Deadline in String format */
    private String due;

    /**
     * Initialises Deadlines using description and deadline.
     *
     * @param description
     * @param deadline
     */
    public Deadlines(String description, String deadline) {
        super(description);
        this.due = deadline;
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Initialises Deadlines using description, deadline and isDone.
     * Used when knowledge about isDone is needed, eg. loading existing list from hard disk.
     *
     * @param description
     * @param deadline
     * @param isDone
     */
    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.due = deadline;
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns string format of deadline.
     *
     * @return String description of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns string format of deadline that will be written on a text file.
     *
     * @return String description of deadline
     */
    @Override
    public String writeToFile() { return "D" + super.writeToFile() + " | " + due; }
}
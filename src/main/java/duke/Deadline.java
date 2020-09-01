package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a task with a specified deadline.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructor for Deadline object.
     * @param isComplete Boolean to indicate if deadline is complete
     * @param index Index position of Task in TaskList
     * @param instructions Task description
     * @param date Deadline
     */
    public Deadline(boolean isComplete, int index, String instructions, String date) {
        super(isComplete, index, instructions);
        this.date = parseTime(date);
    }

    /**
     * Constructor for Deadline object
     * @param isComplete Boolean to indicate if deadline is complete
     * @param index Index position of Task in TaskList
     * @param instructions Task description
     * @param date Deadline in LocalDate form
     */
    public Deadline(boolean isComplete, int index, String instructions, LocalDate date) {
        super(isComplete, index, instructions);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Converts time in String format to LocalDate format.
     * @param time A String represented as "yyyy-MM-d"
     * @return A LocalDate object
     */
    public LocalDate parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        if (this.isComplete) {
            return "[D][✓] " + this.instructions + " (by: " + outputFormatter.format(this.date) + ")";
        } else {
            return "[D][✗] " + this.instructions + " (by: " + outputFormatter.format(this.date) + ")";
        }
    }
}

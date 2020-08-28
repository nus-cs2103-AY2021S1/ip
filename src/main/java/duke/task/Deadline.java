package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * @author Tee Kok Siang
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Task description.
     * @param by Task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns formatted deadline task information.
     * It will be used to write into the file.
     * @return Formatted deadline task information.
     */
    @Override
    public String toFileString() {
        LocalDate localDate = LocalDate.parse(by);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String done = super.isDone ? "1" : "0";
        return "D | " + done + " | " + super.description + " | " +  formattedDate;
    }

    @Override
    public String toString() {
        LocalDate localDate = LocalDate.parse(by);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
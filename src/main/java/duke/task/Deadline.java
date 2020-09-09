package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author Tee Kok Siang
 */
public class Deadline extends Task {
    /** Keyword for reading and writing from/to a file */
    public static final String DEADLINE_KEYWORD = "D";
    /** Keyword for extracting the Deadline date */
    public static final String BY_KEYWORD = "/by";
    /** Position for the keyword */
    public static final int INVALID_BY_POSITION = 1;
    private final String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Task description.
     * @param by Task deadline.
     */
    public Deadline(String description, String by) {
        super(description, DEADLINE_KEYWORD);
        this.by = by;
    }

    /**
     * Returns formatted deadline task information.
     * It will be used to write into the file.
     *
     * @return Formatted deadline task information.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
    }

    @Override
    public String toString() {
        LocalDate localDate = LocalDate.parse(by);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + " (by: " + formattedDate + ")";
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    /**
     * Initializes Deadline object.
     *
     * @param description Description of the deadline.
     * @param by The date of the deadline. In the format yyyy-mm-dd.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private String parseDateTime(String by) {
        LocalDate d = LocalDate.parse(by);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Deadline information.
     *
     * @return Information regarding the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDateTime(by) + ")";
    }

    /**
     * Deadline information in the format to be written to duke.txt.
     *
     * @return Information regarding the deadline.
     */
    @Override
    public String toFileString() {
        return "D\n" + super.getDone() + "\n" + super.toFileString() + "\n" + by + "\n\n";
    }
}
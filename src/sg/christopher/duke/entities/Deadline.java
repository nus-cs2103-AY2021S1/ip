/**
 * Wrapper class for deadlines.
 */
package sg.christopher.duke.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /**
     * Creates a brand new Deadline task.
     * @param description description of the task
     * @param deadline deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        parseDeadline(deadline);
    }

    private String deadline;
    private LocalDate ldDeadline;

    private void parseDeadline(String deadline) {
        try {
            LocalDate ld = LocalDate.parse(deadline);
            ldDeadline = ld;
        } catch (DateTimeParseException dtpe) {
            this.deadline = deadline;
        }
    }

    private String getDeadlineString() {
        if (ldDeadline != null) {
            return ldDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return deadline;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineString() + ")";
    }
}

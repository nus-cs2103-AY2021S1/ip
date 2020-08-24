package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate deadline;

    /**
     * Deadline constructor
     *
     * @param description Description string without date
     * @param deadline Deadline LocalDate object
     * @param isDone Completion status of deadline
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.deadline.format(formatter) + ")";
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event
     *
     * @param description description of event task.
     * @param at          due date of event task (in dd-mm-yyyy format).
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event
     *
     * @param description description of event task.
     * @param isDone      whether or not a task has been done.
     * @param at          due date of event task (in dd-mm-yyyy format).
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description);
        this.isDone = isDone;
        this.at = at;
    }

    /**
     * Returns task printing with proper formatting.
     *
     * @return String with task formatting
     */
    public String saveText() {
        return "E | " + getStatusIcon() + " | " + description + " | " + at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}

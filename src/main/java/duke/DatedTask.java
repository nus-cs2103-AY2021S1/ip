package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles addition of date-based Tasks.
 */

public class DatedTask extends Task {
    /** Date of task */
    protected LocalDate date;

    /**
     * Constructor for Dated Tasks.
     * @param name Description of duke.Task.
     * @param date Date of duke.Task.
     */
    public DatedTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructor for Dated Tasks.
     * @param name Description of duke.Task.
     * @param completed State of completion of duke.Task.
     * @param date Date of duke.Task.
     */
    public DatedTask(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    /**
     * Represents duke.Task in String form.
     * @return String representation of duke.Task object.
     */
    @Override
    public String format() {
        return super.format() + SAVE_DELIMITER + this.date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }
}

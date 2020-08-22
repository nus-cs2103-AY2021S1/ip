package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles deadline-based Tasks.
 */

public class Deadline extends DatedTask {
    /**
     * Constructor for duke.Deadline-based Tasks.
     * @param name Description of duke.Task.
     * @param deadline duke.Deadline of duke.Task.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name, deadline);
    }

    /**
     * Constructor for duke.Deadline-based Tasks.
     * @param name Description of duke.Task.
     * @param completed State of completion of duke.Task.
     * @param deadline duke.Deadline of duke.Task.
     */
    public Deadline(String name, boolean completed, LocalDate deadline) {
        super(name, completed, deadline);
    }

    /**
     * Represents duke.Deadline in format to be saved.
     * @return Saved representation of duke.Deadline object.
     */
    @Override
    public String format() {
        return "D" + SAVE_DELIMITER + super.format();
    }

    /**
     * Represents duke.Deadline in String form.
     * @return String representation of duke.Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

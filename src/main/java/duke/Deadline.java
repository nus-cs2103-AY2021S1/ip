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
     * Constructor for duke.Deadline-based Tasks with Tags.
     * @param name Description of duke.Task.
     * @param deadline duke.Deadline of duke.Task.
     * @param tags Tags associated with Task.
     */
    public Deadline(String name, LocalDate deadline, String[] tags) {
        super(name, deadline, tags);
    }

    /**
     * Constructor for duke.Deadline-based Tasks with completed state.
     * @param name Description of duke.Task.
     * @param isCompleted State of completion of duke.Task.
     * @param deadline duke.Deadline of duke.Task.
     */
    public Deadline(String name, boolean isCompleted, LocalDate deadline) {
        super(name, isCompleted, deadline);
    }

    /**
     * Constructor for duke.Deadline-based Tasks with completed state and tags.
     * @param name Description of duke.Task.
     * @param isCompleted State of completion of duke.Task.
     * @param deadline duke.Deadline of duke.Task.
     * @param tags Tags associated with Task.
     */
    public Deadline(String name, boolean isCompleted, LocalDate deadline, String[] tags) {
        super(name, isCompleted, deadline, tags);
    }

    /**
     * Represents duke.Deadline in format to be saved.
     * @return Saved representation of duke.Deadline object.
     */
    @Override
    public String format() {
        return "D" + getDelimiter() + super.format();
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

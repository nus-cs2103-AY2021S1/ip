package duke.tasks;

import java.time.LocalDate;

/**
 * Class to define a deadline.
 */
public class Deadline extends TimedTask {

    /**
     * Creates a Deadline with the given task name and deadline.
     *
     * @param task Task name
     * @param ddl deadline
     */
    public Deadline(String task, LocalDate ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDateTime());
    }

    /**
     * Returns string representation of the Deadline to store in file.
     *
     * @return string representation
     */
    @Override
    public String fileString() {
        return String.format("D|%s|%s", super.fileString(), ddl.toString());
    }
}

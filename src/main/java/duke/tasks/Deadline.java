package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline tasks created by users.
 *
 */
public class Deadline extends Task{
    private LocalDate time;

    /**
     * Creates Deadline object with relevant task name and deadline time.
     *
     * @param taskName Task name.
     * @param time Time that the task is due (YYYY-MM-DD format).
     */
    public Deadline(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Returns String representation of deadline task in backend data storage.
     *
     * @return Representation of deadline task in backend data storage.
     */
    public String getDataStorageName() {
        return "D | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName() + " | "
                + this.time;
    }

    /**
     * Returns String representation of task to be displayed in task list.
     *
     * @return Representation of task in task list.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

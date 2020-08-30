package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event tasks created by users.
 *
 */
public class Event extends Task {
    private LocalDate time;

    /**
     * Creates Event object with relevant task name and event time.
     *
     * @param taskName Task name.
     * @param time Time that the task is due (YYYY-MM-DD format).
     */
    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Returns String representation of event task in backend data storage.
     *
     * @return Representation of event task in backend data storage.
     */
    public String getDataStorageName() {
        return "E | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName() + " | "
                + this.time;
    }

    /**
     * Returns String representation of event task to be displayed in task list.
     *
     * @return Representation of event task in task list.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

package util.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline inherits from Task.
 * Deadline is a Task that has an added characteristic of a dueDate.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Constructor.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.dueDate = date;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline in String format.
     */
    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.dueDate.format(formatter);
    }

    @Override
    public String toSaveData() {
        return "D - " + super.toSaveData() + " - " + this.dueDate + "\n";
    }

    @Override
    public String toString() {
        // By default print task name
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}

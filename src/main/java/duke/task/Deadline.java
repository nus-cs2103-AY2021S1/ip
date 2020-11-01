package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.parser.DateParser;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private String dueTimeStr;
    private LocalDate dueTime;
    private boolean isInDateFormat;

    /**
     * Create a <code>Deadline</code> object
     * @param description The description of the task
     * @param dueTime The time at which the task is due.
     */
    public Deadline(String description, String dueTime) {
        super(description);
        parseDueTime(dueTime);
    }

    private void parseDueTime(String dueTime) {
        assert dueTime != null : "due time cannot be null";
        this.dueTimeStr = dueTime;
        Optional<LocalDate> optDate = DateParser.parse(dueTime);
        if (optDate.isPresent()) {
            this.dueTime = optDate.get();
            isInDateFormat = true;
        } else {
            isInDateFormat = false;
        }
    }

    /**
     * Represents the type of this task through an icon.
     * @return An icon
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Converts the task to string.
     * @return The string representation of this task
     */
    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description + " (by: "
                + (isInDateFormat
                        ? dueTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                        : dueTimeStr)
                + ")";
    }

    /**
     * Checks whether this task is due on a specified day.
     * @param cmpDate A date that is being queried
     * @return True if this task is due on that day, false otherwise
     */
    public boolean isDueOn(LocalDate cmpDate) {
        assert cmpDate != null : "compared date cannot be null";
        if (!isInDateFormat) {
            return false;
        }
        return cmpDate.isEqual(dueTime);
    }

    public void setTime(String newDueTimeStr) {
        parseDueTime(newDueTimeStr);
    }

    /**
     * Gets the time that this task is due.
     * @return The time string input by the user
     */
    @Override
    public String getTime() {
        return dueTimeStr;
    }

    @Override
    public Task clone() {
        Task clonedTask = new Deadline(super.description, dueTimeStr);
        return this.isDone ? clonedTask.markAsDone() : clonedTask;
    }
}

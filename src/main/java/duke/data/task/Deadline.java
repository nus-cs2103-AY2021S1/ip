package duke.data.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a Deadline as a Task with
 * a due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructor for Deadline, initialises description
     * in the base class and also the dueDate.
     * @param description description of the task.
     * @param dueDate date indicating when the task is due.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        String month = dueDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        int day = dueDate.getDayOfMonth();
        int year = dueDate.getYear();
        String timeDisplay = String.format("%d %s %d", day, month, year);
        return "[D]" + super.toString() + String.format(" (by: %s)", timeDisplay);
    }
}
